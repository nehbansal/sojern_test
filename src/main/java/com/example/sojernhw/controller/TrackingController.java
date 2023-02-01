package com.example.sojernhw.controller;

import com.example.sojernhw.service.TrackingService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/tracking")
public class TrackingController {
    Logger LOG = LoggerFactory.getLogger(TrackingController.class);
    @Autowired
    TrackingService trackingService;

    /**
     * curl -X GET \
     *   http://localhost:8080/tracking/ping \
     *   -H 'cache-control: no-cache' \
     *   -H 'postman-token: b81313ba-5321-f882-014b-79e85fec2f7b'
     *
     *
     * @return
     * @throws IOException
     */
    @GetMapping ("/ping")
    ResponseEntity<String> ping() throws IOException {
        if(!trackingService.fileExist()) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(),
                    HttpStatus.OK);
        }
    }

    /**
     * curl -X GET \
     *   http://localhost:8080/tracking/img \
     *   -H 'cache-control: no-cache' \
     *   -H 'postman-token: 5e0f9301-6fa1-25f7-c2b1-59ece8bbeeb0'
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/img",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
        LOG.info("Received image download request");
        InputStream in = getClass()
                .getResourceAsStream("/static/one_cross_one.gif");
        return ResponseEntity.ok()
                .body(new InputStreamResource(in));
    }
}
