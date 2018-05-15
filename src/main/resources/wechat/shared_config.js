const wxConfig = {
    appId: 'wxc8676e52af4c87ed',
    timestamp: $timestamp,
    nonceStr: '$nonceStr',
    signature: '$signature',
    title: '$title',
    desc: '$desc',
    link: '$link'
};
wx.config({
    debug: false,
    appId: wxConfig.appId,
    timestamp: parseInt(wxConfig.timestamp),
    nonceStr: wxConfig.nonceStr,
    signature: wxConfig.signature,
    jsApiList: [
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareQZone'
    ]
});
wx.ready(function(){
    wx.onMenuShareTimeline({
        title: wxConfig.title,
        link: wxConfig.link,
        imgUrl: 'http://house.jinfeibiao.com/static/share.jpg',
        success: function () {},
        complete: function () {}
    });
    wx.onMenuShareAppMessage({
        title: wxConfig.title,
        desc: wxConfig.desc,
        link: wxConfig.link,
        imgUrl: 'http://house.jinfeibiao.com/static/share.jpg',
        type: 'link',
        dataUrl: '',
        success: function () {},
        complete: function () {}
    });
    wx.onMenuShareQQ({
        title: wxConfig.title,
        desc: wxConfig.desc,
        link: wxConfig.link,
        imgUrl: 'http://house.jinfeibiao.com/static/share.jpg',
        success: function () {},
        cancel: function () {}
    });
    wx.onMenuShareQZone({
        title: wxConfig.title,
        desc: wxConfig.desc,
        link: wxConfig.link,
        imgUrl: 'http://house.jinfeibiao.com/static/share.jpg',
        success: function () {},
        cancel: function () {}
    });
});