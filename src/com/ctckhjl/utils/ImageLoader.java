package com.ctckhjl.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLoader implements Runnable {
	private String path;
	private Map<String, BufferedImage> imgPools;
	private ImageDependency imgNeeder;
	private Thread runner;
	public static final int FLIP_HORIZONTAL = 0;
	public static final int FLIP_VERTICAL = 1;

	public ImageLoader(String path) {
		super();
		setPath(path);
		this.imgPools = new HashMap<String, BufferedImage>();
	}

	private void setPath(String p) {
		if (p == null)
			throw new IllegalArgumentException("Path is null!");
		this.path = p;
	}

	@Override
	public void run() {
		File f = new File(path);
		if (!(f.exists() && f.isDirectory() && f.listFiles() != null))
			return;
		File[] fs = f.listFiles();
		for (File sF : fs) {
			if (sF.isDirectory())
				getFiles(sF.getPath(), sF.getName());
		}
		this.imgNeeder.setAnimations(new HashMap<String, BufferedImage>(this.imgPools));

	}

	private void getFiles(String absolutePath, String name) {
		File f = new File(absolutePath);
		if (!(f.exists() && f.isDirectory() && f.listFiles() != null))
			return;
		File[] fs = f.listFiles();
		for (File sF : fs) {
			if (sF.isDirectory())
				getFiles(sF.getAbsolutePath(), name + "." + sF.getName());
			else {
				// load file into map
				String currentName = sF.getName();
				String extendsion = ".png";
				String fullName = name + "." + currentName.substring(0, currentName.length() - extendsion.length());
				BufferedImage img;
				try {
					img = ImageIO.read(sF);
					imgPools.put(fullName, img);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	public void load(ImageDependency parent) {
		if (runner == null) {
			this.imgNeeder = parent;
			runner = new Thread(this, "Image-Loader-Path-" + this.path);
			runner.start();
		}
	}

	public BufferedImage rotation(BufferedImage src, double angle) {
		int transparency = src.getColorModel().getTransparency();
		BufferedImage des = new BufferedImage(src.getWidth(), src.getHeight(), transparency);
		Graphics2D g = (Graphics2D) des.createGraphics();
		g.drawImage(src, 0, 0, null);
		AffineTransform tx = new AffineTransform();
		g.translate(src.getWidth() >> 1, src.getHeight() >> 1);
		tx.rotate(angle, src.getWidth() >> 1, src.getHeight() >> 1);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		des = op.filter(des, null);
		return des;
	}

	public BufferedImage flipImage(BufferedImage src, int type) {
		BufferedImage des = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) des.createGraphics();
		if (type == FLIP_VERTICAL) {
			g.drawImage(src, 
					src.getWidth(), 0, 0,src.getHeight(), 
					0 , 0, src.getWidth(), src.getHeight(), 
					null);
			
		}
		if (type == FLIP_HORIZONTAL) {
			g.drawImage(src,
					0, 0, src.getWidth(), src.getHeight(),
					src.getWidth(), 0, 0, src.getHeight(),
					null);

		}
		g.dispose();
		return des;
	}
}
