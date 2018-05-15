package com.jinhui.service.batch;

import java.util.List;

public interface ItemWriter<T> {

	void write(List<T> items);

}