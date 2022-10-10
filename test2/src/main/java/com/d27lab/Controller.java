package com.d27lab;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller/*")
public class Controller extends HttpServlet {
    private String flag = "MexiLu";
    private String getFlag(){
        return flag;
    }


    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        PrintWriter out = response.getWriter();

        out.print(response);
        out.print(getFlag());
        out.flush();
        System.out.println(getFlag());
        System.out.println(System.getProperty("java.version"));
        return;

    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String line = null;
        StringBuffer inputXML = new StringBuffer();

        while ((line = br.readLine()) != null) {
            inputXML.append(line);
        }
        br.close();

        XStream xstream = new XStream(new DomDriver());
        String s = (String) xstream.fromXML(inputXML.toString());

        //send to response
        PrintWriter out = response.getWriter();

        out.print(s);
        out.flush();


    }
}
