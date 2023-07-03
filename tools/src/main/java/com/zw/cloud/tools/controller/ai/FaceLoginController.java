package com.zw.cloud.tools.controller.ai;
/*
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.tools.service.impl.FaceLoginServiceImpl;
import com.zw.cloud.tools.utils.BaiduAiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/tools/face/login")
public class FaceLoginController {

    @Autowired
    private FaceLoginServiceImpl faceLoginService;
    @Autowired
    private BaiduAiUtil baiduAiUtil;

    *//**
     * 获取刷脸登录二维码
     *//*
    @GetMapping(value = "/qrcode")
    //http://localhost:9040/tools/face/login/qrcode
    public WebResult qrcode() throws Exception {
        return WebResult.success().withData(faceLoginService.getQRCode());
    }

    *//**
     * 检查二维码：登录页面轮询调用此方法，根据唯一标识code判断用户登录情况
     *//*
    @RequestMapping(value = "/qrcode/{code}")
    //http://localhost:9040/tools/face/login/qrcode/1
    public WebResult qrcodeCeck(@PathVariable(name = "code") Integer code) throws Exception {
        return WebResult.success().withData(faceLoginService.checkQRCode(code));
    }

    *//**
     * 当用户扫码进入落地页，通过落地页打开摄像头，并且定时成像。将成像图片，通过接 口提交给后端进行人脸检测。
     *
     * @param file
     * @return
     * @throws Exception
     *//*
    @PostMapping(value = "/checkFace")
    //http://localhost:9040/tools/face/login/checkFace
    public WebResult checkFace(@RequestParam(name = "file") MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("file is null");
        }
        Boolean aBoolean = baiduAiUtil.faceCheck(Base64Utils.encodeToString(file.getBytes()));
        return aBoolean ? WebResult.success() : WebResult.failed();
    }


    *//**
     * 检测成功后，即进行人脸登录，人脸登录后，改变特殊标记状态值，成功为“1”，失败为“0”。
     * 当登录成功时， 进行自动登录操作，将token和userId存入到redis中。
     * @param code
     * @param file
     * @return
     * @throws Exception
     *//*
    @PostMapping(value = "/success/{code}")
    //http://localhost:9040/tools/face/login/success/
    public WebResult loginByFace(@PathVariable(name = "code") Integer code,
                                 @RequestParam(name = "file") MultipartFile file) throws Exception {
        boolean result =  faceLoginService.loginByFace(code, file);
        return result ? WebResult.failed() : WebResult.success();
    }
}*/

    /**
     * 前端 二维码展现
     */
    // 二维码  
    /*handlecode() {   qrcode().then(res => {
    
    this.param.qrcode = res.data.file    this.centerDialogVisible = true    this.codeCheckInfo = res.data.code    setInterval(() => {     if (this.states === '-1') {     codeCheck({ code: res.data.code }).then(res => {      this.states = res.data.state      this.token = res.data.token      if (this.states === '0') {      // 登录      this.$store        .dispatch('LoginByCode', res.data.token)        .then(() => {        this.$router.push({ path: '/' })        })        .catch(() => {        })       }       if (this.states === '1') {       // 关闭       this.centerDialogVisible = false       }      })     }     }, 1000 * 10)    })   }
*/
    /**
     * 落地页调用摄像头
     */

    /*handleClick() {   let _this = this
 
   if (!this.vdstate) {    return false    }   if (!_this.states) {
            // 注册拍照按钮的单击事件
            let video = this.$refs['vd']   let canvas = this.$refs['cav']
            let form = this.$refs["myForm"];   let context = canvas.getContext('2d')
            // 绘制画面
            context.drawImage(video, 0, 0, 200, 200)   let base64Data = canvas.toDataURL('image/jpg')
 
            // 封装blob对象
            let blob = this.dataURItoBlob(base64Data, 'camera.jpg')
            // base64 转图片file
            let formData = new FormData()   formData.append('file', blob)
 
   this.imgUrl = base64Data

   checkFace(formData).then(res => {    if (res.data.isSuc) {     axios({      method: 'post',      url: '/api/frame/facelogin/' + this.$route.query.code,      data: formData      })       .then(function(response) {       console.log(response)       _this.states = true       _this.canvasShow = false       _this.tipShow = true       // _this.$message.success('验证通过' + '!')       })       .catch(function(error) {       console.log(error)       })     } else {     return false     }    })    }
 
   },  dataURItoBlob(base64Data) {   var byteString   if (base64Data.split(',')[0].indexOf('base64') >= 0)    byteString = atob(base64Data.split(',')[1])   else byteString = unescape(base64Data.split(',')[1])   var mimeString = base64Data     .split(',')[0]     .split(':')[1]     .split(';')[0]   var ia = new Uint8Array(byteString.length)   for (var i = 0; i < byteString.length; i++) {    ia[i] = byteString.charCodeAt(i)    }   return new Blob([ia], { type: mimeString })   }  }

        }*/