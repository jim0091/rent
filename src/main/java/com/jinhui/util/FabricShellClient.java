package com.jinhui.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ExecCreation;
import com.spotify.docker.client.shaded.com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jinhui on 2018/1/24.
 */
public class FabricShellClient {

    private static Logger logger = LoggerFactory.getLogger(FabricShellClient.class);
    private static final String tmpCmdFile = "tmp_fabric_cmd.sh";
    private static final String ContainerName = "fabric-cli";
    private static final String AppName = "rent";
    private static final String FabricChannel = "businesschannel";
    private static final String QueryResultIdentifier = "Query Result:";
    private static final int QueryResultIdentifierLength = QueryResultIdentifier.length();
    private static final JSONObject SuccessfulResult = JSON.parseObject("{code:\"0\"}");
    private static final JSONObject FailureResult = JSON.parseObject("{code:\"1\"}");

    private static Container CLI = null;
    private String cmdDir;
    private String fabricUrl;

    private static final String InvokeCMD = "peer chaincode invoke -o orderer.example.com:7050 " +
            "--tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA " +
            "-C " + FabricChannel + " -n "+AppName+" -c ";
    private static final String QueryCMD = "peer chaincode query -C "+ FabricChannel +" -n "+AppName+"  -c ";

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void asycExec(Method method, String[] args) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    execCall(method, args);
                } catch (DockerException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     *
     * @param method
     * @param args
     * @return json结果
     */
    public String query(Method method, String[] args) {
        try {
            String result = execCall(method, args);
            String jsonResult = extractQueryResult(result);
            return jsonResult;
        } catch (DockerException | InterruptedException ex) {
            throw new FabricException(ex);
        }
    }

    public enum Method implements MethodAttribute{
        StoreJsonString("存储JsonString"),
        QueryJsonString("查询JsonString") {
            @Override
            public boolean isInvoke() {
                return false;
            }
        },
        QueryHistoryJsonString("查询QueryHistoryJsonString") {
            @Override
            public boolean isInvoke() {
                return false;
            }
        };
        @Override
        public boolean isInvoke() {
            return true;
        }
        private String text;
        Method(String text) {
            this.text = text;
        }
    }

    private interface MethodAttribute {
        boolean isInvoke();
    }

    public static void main(String[] strs) {
        /*FabricShellClient fabricShellClient = new FabricShellClient();
        fabricShellClient.execCall(Method.QueryJsonString, new String[]{"QueryHashcode"});*/
        System.out.println("12345678".indexOf("456"));
    }

    private String execCall(Method method, String[] args)
            throws DockerException, InterruptedException {
        StringBuilder pstr = new StringBuilder();
        pstr.append("'{\"Args\":[\"");
        pstr.append(method.toString()).append("\",");
        for(String arg:args) {
            pstr.append("\"").append(arg).append("\",");
        }
        pstr.deleteCharAt(pstr.length()-1);
        pstr.append("]}'");
        String cmd = (method.isInvoke() ? InvokeCMD : QueryCMD)
                + pstr.toString();

        try (FileWriter fw = new FileWriter(cmdDir+File.separator+tmpCmdFile)) {
            fw.write(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(logger.isInfoEnabled()) {
            logger.info("执行fabric业务, 参数: {}", pstr.toString());
        }
        String result = invoke();
        /*if(method.isInvoke()) {
            //for (int i = 0; i < 3; i++) {
            //执行多次，保证存入数据
            result = invoke();
            //}
        } else {
            result = invoke();
        }*/
        return result;
    }

    private static String extractQueryResult(String result) {
        int resultStartIndex = result.indexOf(QueryResultIdentifier)
                + QueryResultIdentifierLength;
        if(resultStartIndex < 0) {
            throw  new FabricException();
        }
        result = result.substring(resultStartIndex);
        int resultEndIndex = result.indexOf("\n");
        return result.substring(0, resultEndIndex).trim();
    }

    public void setCmdDir(String cmdDir) {
        this.cmdDir = cmdDir;
    }

    public void setFabricUrl(String fabricUrl) {
        this.fabricUrl = fabricUrl;
    }

    private String invoke() throws DockerException, InterruptedException {
        //final DockerClient docker = DefaultDockerClient.fromEnv().build();
        final DockerClient docker = DefaultDockerClient.builder()
            .uri(URI.create(fabricUrl))
            .build();
        //new DefaultDockerClient("unix:///var/run/docker.sock");
        Container container = containerByName(docker, ContainerName);
        if(container == null)
            return null;
        final ExecCreation execCreation = docker.execCreate(
                container.id(),
                new String[]{"bash", "/tmp/scripts/tmp_fabric_cmd.sh"},
                DockerClient.ExecCreateParam.attachStdout(),
                DockerClient.ExecCreateParam.attachStderr());

        try (final LogStream stream = docker.execStart(execCreation.id())) {
            final String output = stream.readFully();
            //ignored output
            return output;
        }
    }

    private static Container containerByName(DockerClient docker, String containerName)
            throws DockerException, InterruptedException {
        if(CLI == null) {
            List<Container> containers = docker.listContainers();
            for (Container container : containers) {
                ImmutableList<String> names = container.names();
                for (String name : names) {
                    if (name.contains(containerName))
                        CLI = container;
                }
            }
        }
        return CLI;
    }
}
