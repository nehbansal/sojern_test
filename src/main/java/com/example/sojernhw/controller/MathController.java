package com.example.sojernhw.controller;

import com.example.sojernhw.dto.MathRequest;
import com.example.sojernhw.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    MathService mathService;

    /**
     * curl -X POST \
     *   http://localhost:8080/math/min \
     *   -H 'cache-control: no-cache' \
     *   -H 'content-type: application/json' \
     *   -H 'postman-token: f90f5bec-bb5e-38aa-63fb-9dd69f619982' \
     *   -d '{
     * 	"numbers" : [3,7,2,4,9,8],
     * 	"count": 3
     * }'
     *
     * output - [3,2,4]
     *
     * @param request
     * @return
     */
    @PostMapping("/min")
    public ResponseEntity<List<Integer>> minK(@RequestBody MathRequest request) {
        List<Integer> numbers = request.getNumbers();
        return ResponseEntity.ok(mathService.getKMin(numbers, request.getCount()));
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/math/max \
     *   -H 'cache-control: no-cache' \
     *   -H 'content-type: application/json' \
     *   -H 'postman-token: ed1c169f-7a86-b270-3fa1-1a5dc4055195' \
     *   -d '{
     * 	"numbers" : [3,7,2,4,9,8],
     * 	"count": 3
     * }'
     *
     * output - [7,9,8]
     *
     * @param request
     * @return
     */
    @PostMapping("/max")
    public ResponseEntity<List<Integer>> maxK(@RequestBody MathRequest request) {
        List<Integer> numbers = request.getNumbers();
        return ResponseEntity.ok(mathService.getKMax(numbers, request.getCount()));
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/math/average \
     *   -H 'cache-control: no-cache' \
     *   -H 'content-type: application/json' \
     *   -H 'postman-token: 08615687-3846-cfc2-a3f8-59382bb83760' \
     *   -d '[3,7,2,4,9,8]'
     *
     *   output - 5.5
     *
     * @param request
     * @return
     */
    @PostMapping("/avg")
    public ResponseEntity<Double> average(@RequestBody List<Integer> request) {
        return ResponseEntity.ok(mathService.average(request));
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/math/median \
     *   -H 'cache-control: no-cache' \
     *   -H 'content-type: application/json' \
     *   -H 'postman-token: 295eeb10-085c-5e35-8d60-6916382de1c6' \
     *   -d ' [3,7,2,4,9,8,1]'
     *
     *   output - 4.0
     *
     * @param request
     * @return
     */
    @PostMapping("/median")
    public ResponseEntity<Double> median(@RequestBody List<Integer> request) {
        return ResponseEntity.ok(mathService.median(request));
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/math/percentile \
     *   -H 'cache-control: no-cache' \
     *   -H 'content-type: application/json' \
     *   -H 'postman-token: fbabe118-7646-ccce-0287-cea9a6d6ffdc' \
     *   -d ' {"numbers":[3, 6, 7, 8, 8, 9, 10, 13, 15, 16, 20],
     *  "count":50}'
     *   output - 9
     *
     * @param request
     * @return
     */
    @PostMapping("/percentile")
    public ResponseEntity<Long> percentile(@RequestBody MathRequest request) {
        return ResponseEntity.ok(mathService.percentile(request.getNumbers(),
                request.getCount()));
    }
}
