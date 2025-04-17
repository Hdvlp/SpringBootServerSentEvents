package com.springbootsse.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class WebController {

    @GetMapping("/")
    public String getShowSse(HttpServletResponse response) {
        response.addHeader("Content-type", "text/html;charset=utf8");
        return new String("""
            <!doctype html>
            <html><head></head>
            <body>
            <div>Event</div>
            <div id="orders"></div>
                <script>


                    let eventSource;

                    function connectSSE() {
                        if (eventSource) {
                            console.log("Closing previous SSE connection...");
                            eventSource.close(); // Properly close the old connection
                        }

                        console.log("Creating new SSE connection...");
                        eventSource = new EventSource("//localhost:8080/api/v1/sse/subscribe");

                        eventSource.addEventListener('order', (event) => {
                            const orderResponse = ('[✅ Event received]', event.data);
                            console.log(orderResponse);
                            const element = document.getElementById("orders");
                            
                            element.innerHTML = element.innerHTML + " <div>" + orderResponse + "</div>";

                        });

                        eventSource.onmessage = (event) => {
                            console.log("Received:", event.data);
                            
                        };


                        eventSource.onerror = () => {
                            console.error("SSE error detected:", event);
                            setTimeout(connectSSE, 5000); 
                        };
                    }

                    // Start initial connection
                    connectSSE();
               


                
                </script>
                </body></html>
                """);
    }
    

}
