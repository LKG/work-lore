<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>用户密码找回</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" />
    <style type="text/css">
        .text-sj{
            text-indent: 2em; /*em是相对单位，2em即现在一个字大小的两倍*/
        }
        body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}
        td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}
        pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}
        th,td{font-family:arial,verdana,sans-serif;line-height:1.666}
        img{ border:0}
        header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}
        blockquote{margin-right:0px}
    </style>
    <style id="ntes_link_color" type="text/css">a,td a{color:#064977}</style>
</head>
<body tabindex="0" role="listitem">
<table cellspacing="0" cellpadding="0" width="760px" style="border-spacing: 0; color: #333333; border: 1px solid #f1f1f1; margin-left: auto; margin-right: auto;">
    <tbody>
    <tr>
        <td width="760"> <img src="http://mobile.12306.cn/weixin/resources/weixin/images/mail/mail_top.jpg" width="760" height="275" /> </td>
    </tr>
    <tr>
        <td width="720" style="padding-left: 20px; padding-right: 20px;">
            <table cellspacing="0" cellpadding="0" width="720px" style="border-spacing: 0; color: #333333;">
                <tbody>
                <tr>
                    <td width="720" style="font-size: 16px; height: 40px; font-weight: bold;"> 尊敬的 <span style="color: #ff764c;">${result.nickName!''} ：</span> </td>
                </tr>
                <tr>
                    <td width="720">
                        <div style="line-height: 20px; font-size: 12px;">
                            您好！
                        </div>
                        <div style="line-height: 20px; font-size: 12px;">
                            您在 ${.now?string['yyyy年MM月dd日 hh时mm分']}  提交找回密码请求，请点击下面的链接修改用户${result.uerName!''} 密码:
                        </div> </td>
                </tr>
                <tr>
                    <td width="720" style="padding-top: 10px; padding-bottom: 10px;">
                        <div style="border-top: 1px dashed #e9ecf0; border-bottom: 1px dashed #e9ecf0; color: #000000; font-size: 14px; padding-top: 10px; padding-bottom: 10px;">
                            <div style="line-height: 20px; width:720px; color: #000000; padding-top: 5px; padding-bottom: 5px; font-weight: bold;">
                                <a href="http://http://www.tabis.cn/findPwd/checkEmailCode.jhtml?k=${k}&emailCode=${emailCode}">
                                    http://www.tabis.cn/findPwd/checkEmailCode.jhtml?k=${k}&emailCode=${emailCode}
								</a>
                            </div>
                            <div style="line-height: 20px; color: #000000; padding-top: 5px; padding-bottom: 5px; font-weight: bold;">
                                (如果您无法点击这个链接，请将此链接复制到浏览器地址栏后访问)
                            </div>
                            <div style="line-height: 20px; color: #000000; padding-top: 5px; padding-bottom: 5px; font-weight: bold;">
                                为了保证您帐号的安全性，该链接有效期为24小时，并且点击一次后将失效!
                            </div>
                            <div style="line-height: 20px; color: #000000; padding-top: 5px; padding-bottom: 5px; font-weight: bold;">
                                设置并牢记密码保护问题将更好地保障您的帐号安全。
                            </div>
                            <div style="line-height: 20px; color: #000000; padding-top: 5px; padding-bottom: 5px; font-weight: bold;">
                                如果您误收到此电子邮件，则可能是其他用户在尝试帐号设置时的误操作，如果您并未发起该请求，则无需再进行任何操作，并可以放心地忽略此电子邮件。
                            </div>
                            <div style="line-height: 20px; color: #000000; padding-top: 5px; padding-bottom: 5px; font-weight: bold;">
                                若您担心帐号安全，建议您立即登录，进入“我的”，密码修改中修改密码。
                            </div>
                        </div> </td>
                </tr>
                <tr>
                    <td width="720">
                        <table cellspacing="0" cellpadding="0" width="720px" style="border-spacing: 0; color: #333333;">
                            <tbody>
                            <tr>
                                <td></td>
                                <td width="200" style="text-align: center; height: 24px; font-size: 12px;"> GG </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td width="200" style="text-align: center; height: 24px; font-size: 12px;"> ${.now?string['yyyy年MM月dd日']}</td>
                            </tr>
                            </tbody>
                        </table> </td>
                </tr>
                <tr>
                    <td width="720" style="padding-top: 10px; padding-bottom: 15px;"> <img src="http://mobile.12306.cn/weixin/resources/weixin/images/mail/mail_line.jpg" alt="" /> </td>
                </tr>
                </tbody>
            </table> </td>
    </tr>
    </tbody>
</table>
</body>
</html>