package com.example.collectionsframework.c22a2atrsnfer;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author: LiuSaiSai
 * @date: 2020/07/31 21:35
 * @description:
 */
public class SocketManager {
    private ServerSocket ss;
    private Handler handler = null;
    private final String TAG = SocketManager.this.getClass().getSimpleName();
    private final String regEx = "/root/";


    public SocketManager(Handler handler) {
        this.handler = handler;
        int port = 9999;
        while (port > 9000) {
            try {
                //1. 建立连接监听窗口；
                ss = new ServerSocket(port);

                Log.i(TAG, "server.getReuseAddress() = " + ss.getReuseAddress());
                if (!ss.getReuseAddress()) {
                    ss.setReuseAddress(true);
                }
                break;
            } catch (Exception e) {
                port--;
                Log.i(TAG, "SocketManager: e = " + e);
            }
        }
        SendMessage(1, port);
        Thread receiveFileThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.i(TAG, "ReceiveFile  run:    被调用了！  ");
                    ReceiveFile();
                }
            }
        });
        receiveFileThread.start();
    }

    void SendMessage(int what, Object obj) {
        if (handler != null) {
            Message.obtain(handler, what, obj).sendToTarget();
        }
    }

    public void SendFile(ArrayList<String> fileName, ArrayList<String> path, String ipAddress, int port) {
        Log.i(TAG, "SendFile:fileName(0) =  " + fileName.get(0).toString());
        Log.i(TAG, "SendFile:path(0) =  " + path.get(0).toString());

        try {
            Socket name = new Socket(ipAddress, port);
            Log.i(TAG, "SendFile: Socketname =  " + name);
            for (int i = 0; i < fileName.size(); i++) {

                OutputStream outputName = name.getOutputStream();
                OutputStreamWriter outputWriter = new OutputStreamWriter(outputName);
                BufferedWriter bwName = new BufferedWriter(outputWriter);
                bwName.write(fileName.get(i));
                bwName.close();
                outputWriter.close();
                outputName.close();
                name.close();
                SendMessage(0, "正在发送" + fileName.get(i));

                Socket data = new Socket(ipAddress, port);
                Log.i(TAG, "SendFile: Socketdata =  " + data);
                OutputStream outputData = data.getOutputStream();
                Log.i(TAG, "outputData = " + outputData);

                Log.i(TAG, "path.get(i)  = " + path.get(i));

                //File file = new File(path.get(i));
                //以下这行爆出：文件不存在；原因在于默认得到的目录是 带有 /root/前缀的；故输入流无法创建成功；
                String tempPath = path.get(i).replaceAll(regEx, "");
                FileInputStream fileInput = new FileInputStream(tempPath);
                Log.i(TAG, "fileInput = " + fileInput);
                //判断是否读到文件末尾
                int size = -1;
                byte[] buffer = new byte[1024];
                Log.i(TAG, "SendFile: size =  " + size);
                while ((size = fileInput.read(buffer)) > 0) {
                    Log.i(TAG, "SendFile: While : size =  " + size);
                    outputData.write(buffer, 0, size);
                    outputData.flush();
                }
                //告诉服务端，文件已传输完毕
                //data.shutdownOutput();
                Log.i(TAG, "SendFile: While : 完成 " + size);

                outputData.close();
                fileInput.close();
                data.close();
                SendMessage(0, fileName.get(i) + "  发送完成");
                SendMessage(0, "所有文件发送完成");

            }


        } catch (Exception e) {
            Log.i(TAG, "发送错误   +   " + e.getMessage());
            SendMessage(0, "发送错误:\n" + e.getMessage());
        }
    }

    void ReceiveFile() {
        Log.i(TAG, "ReceiveFile:    被用调了！  ");
        try {
            //2. 连接客户端对象
            //阻塞式方法，只有客户端连接了才会继续往下运行
            while (true) {
                Socket name = ss.accept();
                Log.i(TAG, "ReceiveFile  Socket name =   " + name);
                //3.获取客户端发来的数据
                InputStream nameIPS = name.getInputStream();
                Log.i(TAG, "ReceiveFile  :nameIPS  =  " + nameIPS);
                InputStreamReader inputStreamReader = new InputStreamReader(nameIPS);
                Log.i(TAG, "ReceiveFile  inputStreamReader  =  " + inputStreamReader);
                //4.开始读取，获取输入信息
                BufferedReader br = new BufferedReader(inputStreamReader);

                String fileName = br.readLine();
                Log.i(TAG, "ReceiveFile  br  =  " + br + "  fileName = br.readLine() = " + br.readLine());

                br.close();
                inputStreamReader.close();
                nameIPS.close();
                name.close();
                SendMessage(0, "正在接收:" + fileName);

                Socket data = ss.accept();
                Log.i(TAG, "ReceiveFile  Socket data =   " + data);
                //3. 获取客户端发送过来的数据
                InputStream dataIPS = data.getInputStream();
                Log.i(TAG, "ReceiveFile:  dataIPS  =  " + dataIPS);
                //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataIPS));

                String savePath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Screenshots/" + fileName;
                savePath = savePath.replaceAll(regEx, "");
                Log.i(TAG, "ReceiveFile  savePath  =  " + savePath);

                FileOutputStream fos = new FileOutputStream(savePath, false);
                Log.i(TAG, "ReceiveFile FileOutputStream fos   =  " + fos);
                //装载文件名的数组
                byte[] buffer = new byte[1024];
                int size;
                while ((size = dataIPS.read(buffer)) != -1) {
                    Log.i(TAG, "ReceiveFile size  ==  " + size);
                    fos.write(buffer, 0, size);
                    fos.flush();
                }
                fos.flush();
                fos.close();
                //告诉发送端我已经接收完毕
                //OutputStream outputStream = data.getOutputStream();
                fos.close();
                dataIPS.close();
                data.close();
                SendMessage(0, fileName + "接收完成");
            }
        } catch (Exception e) {
            SendMessage(0, "接收错误:\n" + e.getMessage());
        }
    }
}
