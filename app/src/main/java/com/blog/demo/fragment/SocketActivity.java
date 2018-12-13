package com.blog.demo.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by cn on 2017/6/16.
 */

public class SocketActivity extends Activity implements View.OnClickListener {
    private String serverIp = "192.168.4.58";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        findViewById(R.id.btn_tcp_socket).setOnClickListener(this);
        findViewById(R.id.btn_udp_socket).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tcp_socket:
                new TcpSocketTask().execute();
                break;
            case R.id.btn_udp_socket:
                new UdpSocketTask().execute();
                break;
        }
    }

    private class TcpSocketTask extends AsyncTask<String, Integer, Integer> {
        int TCP_SERVER_PORT = 9999;

        protected Integer doInBackground(String... urls) {

            try {
                // 初始化Socket，TCP_SERVER_PORT为指定的端口，int类型
                // 该处IP地址请使用您真实的IP地址
                Socket socket = new Socket(serverIp, TCP_SERVER_PORT);
                // 获取输入流
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                // 生成输出流
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream()));
                // 生成输出内容
                String outMsg = "TCP connecting to " + TCP_SERVER_PORT
                        + System.getProperty("line.separator");
                LogUtil.log("TcpClient", "send: " + outMsg);
                // 写入
                out.write(outMsg);
                // 刷新，发送
                out.flush();
                // 获取输入流
                String inMsg = in.readLine()
                        + System.getProperty("line.separator");
                LogUtil.log("TcpClient", "received: " + inMsg);
                // 关闭连接
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0;
        }
    }

    private class UdpSocketTask extends AsyncTask<String, Integer, Integer> {
        int UDP_SERVER_PORT = 8808;

        @SuppressWarnings("finally")
        protected Integer doInBackground(String... urls) {
            //定义需要发送的信息
            String udpMsg = "hello world from UDP client " + UDP_SERVER_PORT;
            //新建一个DatagramSocket对象
            DatagramSocket ds = null;
            try {
                //初始化DatagramSocket对象
                ds = new DatagramSocket();
                //初始化InetAddress对象
                InetAddress serverAddr = InetAddress.getByName(serverIp);
                DatagramPacket dp;

                //初始化DatagramPacket对象
                dp = new DatagramPacket(udpMsg.getBytes(), udpMsg.length(), serverAddr, UDP_SERVER_PORT);
                //发送
                ds.send(dp);
            }
            //异常处理
            catch (SocketException e) {//Socket连接异常
                e.printStackTrace();
            }catch (UnknownHostException e) {//不能连接到主机
                e.printStackTrace();
            } catch (IOException e) {//数据流异常
                e.printStackTrace();
            } catch (Exception e) {//其它异常
                e.printStackTrace();
            } finally {
                //如果DatagramSocket已经实例化，则需要将其关闭
                if (ds != null) {
                    ds.close();
                }
                return 0;
            }


        }

    }

}
