package com.fkhwl.scf.web.controller;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Random;


@Controller
@RequestMapping("/randomCode")
@Slf4j
public class SecCodeController extends BaseController {

	private Producer kaptchaProducer = null;
	private Properties props = new Properties();
	private Config config = new Config(props);


	@RequestMapping
	public void generate(HttpServletRequest request, HttpServletResponse resp){
		// Set to expire far in the past.
		resp.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		resp.setHeader("Pragma", "no-cache");

		// return a jpeg
		resp.setContentType("image/jpeg");

		// create the text for the image
		String capText = getProducer().createText();

		//		try {
		//			String newSessionId = fkhSSDBHelper.saveSessionValidateCode(1, getSessionId(request), capText);
		//			log.debug("newSessionId: "+newSessionId);
		//			if(null != newSessionId){
		//				super.setCookieTimeout(newSessionId, request, resp);
		//			}
		//		} catch (Exception e) {}


        request.getSession().setAttribute("randomCode", capText);
//        Subject subject = SecurityUtils.getSubject();
//        final Map principal = (Map) subject.getPrincipal();
//        principal.put("randomCode",capText);
        // create the image with the text
		BufferedImage bi = this.kaptchaProducer.createImage(capText);
		ServletOutputStream out;
		try {
			out = resp.getOutputStream();
			// write the data out
			ImageIO.write(bi, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Producer getProducer() {
		props.put("kaptcha.image.width","100");
		props.put("kaptcha.image.height","40");
		props.put("kaptcha.textproducer.char.length","4");
		//		props.put("kaptcha.border","no");
		//		props.put("kaptcha.textproducer.font.color","red");
		props.put("kaptcha.textproducer.font.size","30");
		//		props.put("kaptcha.textproducer.font.color","black");
		props.put("kaptcha.textproducer.char.space","3");
		//		props.put("kaptcha.textproducer.font.names","BKamrnBd");

		props.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
		props.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
		props.put("kaptcha.textproducer.char.string","0123456789");
		if (kaptchaProducer == null) {
			kaptchaProducer = (Producer) config.getProducerImpl();
		}
		return kaptchaProducer;
	}

	private String drawImg(ByteArrayOutputStream output) {
		String code = "";
		for (int i = 0; i < 4; i++) {
			code += randomChar();
		}
		int width = 70;
		int height = 25;
		BufferedImage bi = new BufferedImage(width, height,
			BufferedImage.TYPE_3BYTE_BGR);
		Font font = new Font("Serief", Font.PLAIN, 20);
		Graphics2D g = bi.createGraphics();
		g.setFont(font);
		Color color = new Color(66, 2, 82);
		g.setColor(color);
		g.setBackground(new Color(226, 226, 240));
		g.clearRect(0, 0, width, height);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(code, context);
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = bounds.getY();
		double baseY = y - ascent;
		g.drawString(code, (int) x, (int) baseY);
		g.dispose();
		try {
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}

	private char randomChar(){
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
}
