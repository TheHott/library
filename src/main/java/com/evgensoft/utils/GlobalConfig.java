package com.evgensoft.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class GlobalConfig {
	@Value(value = "${server.port}")
    private int port;
//    @Value(value = "${partners.sizeOfPage}")
//    private int sizePage;
//    @Value(value = "${authentication.admin.login}")
//    private String adminLogin;
//    @Value(value = "${authentication.admin.password}")
//    private String adminPassword;
//    @Value(value = "${images.root.directory}")
//    private String rootDirectoryForImages;
//    @Value(value = "${images.root.url}")
//    private String rootUrlForImages;
}
