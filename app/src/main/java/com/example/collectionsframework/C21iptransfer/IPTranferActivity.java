package com.example.collectionsframework.C21iptransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collectionsframework.R;
import com.lpoint.tcpsocketlib.TcpClient;
import com.lpoint.tcpsocketlib.TcpSocketListener;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author: LiuSaiSai
 * @date: 2020/07/31 21:27
 * @description: 仅实现了 发送功能，并且已经泡桐，但是接收端，没有实现。并没有用上 TcpClientLib 库；
 */
public class IPTranferActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = IPTranferActivity.this.getClass().getSimpleName();
    private Button btn_transfer;
    private TextView tv_result_iptrans;
    private ImageView iv1;
    private ImageView iv2;
     private Socket socket = null;

    private final String SERVER_IP = "192.168.1.10";
    private final int SERVER_PORT = 9999;
    private FileInputStream fis = null;
    private DataOutputStream dos = null;

    private final String First_Image_Start = "begin0:";
    private final String First_Image_End = "end0:";
    private final String Second_Image_Start = "begin1:";
    private final String Second_Image_End = "end1:";
    private final String First_Text_Start = "begin2:";
    private final String Second_Text_Start = "begin3:";
    private final String First_Text_End = "end2:";
    private final String Second_Text_End = "end3:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_tranfer);

        iv1 = (ImageView) findViewById(R.id.iv1_take_photo_ip);
        iv2 = (ImageView) findViewById(R.id.iv2_take_photo_ip);
        tv_result_iptrans = findViewById(R.id.tv_result_iptrans);
        btn_transfer = findViewById(R.id.btn_transfer);

        btn_transfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            String filename = getFilesDir() + File.separator + "CameraPhoto";
            FileInputStream file = new FileInputStream(filename + File.separator + "picture.jpg");
            Log.i(TAG, "run: file0 :  " + filename + File.separator + "picture.jpg");
            FileInputStream file1 = new FileInputStream(filename + File.separator + "qrcode.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(file);
            Bitmap bitmap1 = BitmapFactory.decodeStream(file1);
            Matrix matrix = new Matrix();
            //matrix.setRotate(90);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(),
                    bitmap1.getHeight(), matrix, true);
            iv1.setImageBitmap(bitmap);
            iv2.setImageBitmap(bitmap1);
        } catch (FileNotFoundException e) {
            Log.i(TAG, "文件不存在嗷嗷嗷。。。filename +" + File.separator + "picture.jpg");
            e.printStackTrace();
        }
        Toast.makeText(IPTranferActivity.this, "测试Socket连接", Toast.LENGTH_SHORT).show();

        new MyThread().start();
    }

    public class MyThread extends Thread {//新开一个工作线程
        @Override
        public void run() {
            Log.i(TAG, " 开启了线程；刚进入 run()");

            int length = 0;
            File[] file = new File[4];

            String filename = getFilesDir() + File.separator + "CameraPhoto";

            //filename : /data/user/0/com.example.collectionsframework/files/CameraPhoto
            Log.i(TAG, " 刚进入  run: filename :  " + filename);
            try {
                Log.i(TAG, " 刚进入  run: 有进入第 一11111111 个 try ");
                try {
                    Log.i(TAG, " 刚进入  run: 有进入第 二22222 个 try ");

//                    TcpClient tcpClient = new TcpClient(SERVER_IP, SERVER_PORT);
//                    Log.i(TAG, "run: tcpClient = " + tcpClient + "   tcpClient == null? " + (tcpClient == null));
//                    tcpClient.setTcpSocketListener(new TcpSocketListener() {
//                        @Override
//                        public void onConnException(Exception e) {
//                            Log.i(TAG, " TCP连接异常" +e);
//                        }
//
//                        @Override
//                        public void onMessage(String s) {
//                            Log.i(TAG, "当TCP通道收到消息时执行此回调" + s);
//                        }
//
//                        @Override
//                        public void onListenerException(Exception e) {
//                            Log.i(TAG, "CP消息监听时遇到异常:" + e);
//                        }
//
//                        @Override
//                        public void onSendMsgSuccess(String s) {
//                            Log.i(TAG, "sendMsg()方法成功执行完毕" +s);
//                        }
//
//                        @Override
//                        public void onSendMsgException(Exception e) {
//                            Log.i(TAG, "发送消息时遇到异常， " + e);
//                        }
//
//                        @Override
//                        public void onCloseException(Exception e) {
//                            Log.i(TAG, " 当TCP连接断开时遇到异常 :" + e);
//                        }
//                    });
//                    tcpClient.startConn();

                    socket = new Socket(SERVER_IP, SERVER_PORT);
                    Log.i(TAG, " 刚进入 第 二22222 个 try    socket = " + socket);

                    dos = new DataOutputStream(socket.getOutputStream());
                    Log.i(TAG, " 刚进入 第 二22222 个 try    dos = " + dos);

                    file[0] = new File(filename + File.separator + "qrcode.jpg");
                    Log.i(TAG, "第 二22222 个 try   file[0] = " + filename + File.separator + "qrcode.jpg");
                    file[1] = new File(filename + File.separator + "picture.jpg");
                    file[2] = new File(filename + File.separator + "name.txt");
                    file[3] = new File(filename + File.separator + "price.txt");

                    InputStream image1 = new FileInputStream(file[0]);
                    InputStream image2 = new FileInputStream(file[1]);
                    InputStream text1 = new FileInputStream(file[2]);
                    InputStream text2 = new FileInputStream(file[3]);

                    long fileLength0 = file[0].length();
                    long fileLength1 = file[1].length();
                    long fileLength2 = file[2].length();
                    long fileLength3 = file[3].length();


                    dos.write(First_Image_Start.getBytes());
                    dos.write(file[0].getName().getBytes());
                    dos.write(First_Image_End.getBytes());
                    byte[] bs0 = longToBytes(fileLength0);
                    dos.write(bs0);
                    byte[] b0 = new byte[1024];
                    while ((length = image1.read(b0)) > 0) {
                        Log.i(TAG, "run: dos0  iv1 : bs0.length = " + bs0.length + " ===  Cur length  = " + length);
                        dos.write(b0, 0, length);
                    }

                    dos.write(Second_Image_Start.getBytes());
                    dos.write(file[1].getName().getBytes());
                    dos.write(Second_Image_End.getBytes());
                    byte[] bs1 = longToBytes(fileLength1);
                    dos.write(bs1);
                    byte[] b1 = new byte[1024];
                    while ((length = image2.read(b1)) > 0) {
                        Log.i(TAG, "run: dos1  iv2 : bs1.length = " + bs0.length + " ===  Cur length  = " + length);
                        dos.write(b1, 0, length);
                    }

                    dos.write(First_Text_Start.getBytes());
                    dos.write(file[2].getName().getBytes());
                    dos.write(First_Text_End.getBytes());
                    byte[] bs2 = longToBytes(fileLength2);
                    dos.write(bs2);
                    byte[] b2 = new byte[1024];
                    while ((length = text1.read(b2)) > 0) {
                        Log.i(TAG, "run: dos0  text1 : bs0.length = " + bs2.length + " ===  Cur length  = " + length);
                        dos.write(b2, 0, length);
                    }

                    dos.write(Second_Text_Start.getBytes());
                    dos.write(file[3].getName().getBytes());
                    dos.write(Second_Text_End.getBytes());
                    byte[] bs3 = longToBytes(fileLength3);
                    dos.write(bs3);
                    byte[] b3 = new byte[1024];
                    Log.i(TAG, "b3.legth = " + b3.length );
                    while ((length = text2.read(b3)) > 0) {
                        Log.i(TAG, "run: dos0  text2 : bs0.length = " + bs3.length + " ===  Cur length  = " + length);
                        dos.write(b3, 0, length);
                    }
                } catch (Exception e) {
                    Log.i(TAG, "run: socket = " + socket + "   socket == null? " + (socket == null));
                    e.printStackTrace();
                }
            } finally {
                if (dos != null)
                    try {
                        dos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (fis != null)
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            Log.i(TAG, "run:  over " );
        }
    }

    public static byte[] longToBytes(long n) {
        byte[] b = new byte[8];
        b[7] = (byte) (n & 0xff);
        b[6] = (byte) (n >> 8 & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[0] = (byte) (n >> 56 & 0xff);
        return b;
    }
}