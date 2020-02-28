package com.ticketsys.mgmt.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WrappedRequest extends HttpServletRequestWrapper {
    private String body;
    private HttpServletRequest request;
    int count;
    public WrappedRequest(HttpServletRequest request) throws  IOException{
        super(request);
        this.request = request;
        body="";
        try (BufferedReader bufferedReader = request.getReader())
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
                body += line;
        }
    }
    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream()
        {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
//                readListener.onAllDataRead();
            }

            public int read() throws IOException
            {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
}
