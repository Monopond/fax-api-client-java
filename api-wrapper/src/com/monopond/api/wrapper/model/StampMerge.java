package com.monopond.api.wrapper.model;

import java.awt.Dimension;
import java.awt.Point;
import java.math.BigDecimal;

public class StampMerge {
	
	private StampMerge.Key key;
	private StampMerge.Text textValue;
	private StampMerge.Image imageValue;
	
	
	
	public StampMerge.Key getKey() {
		return key;
	}

	public void setKey(StampMerge.Key key) {
		this.key = key;
	}

	public StampMerge.Text getTextValue() {
		return textValue;
	}

	public void setTextValue(StampMerge.Text textValue) {
		this.textValue = textValue;
	}

	public StampMerge.Image getImageValue() {
		return imageValue;
	}

	public void setImageValue(StampMerge.Image imageValue) {
		this.imageValue = imageValue;
	}

	public static class Key {
		private Point point;
		
		public Key() {
			point = new Point();
		}
		
		public Key(int x, int y) {
			point = new Point(x, y);
		}
		
		public int getX() {
			return point.x;
		}
		
		public void setX(int x) {
			point.x = x;
		}
		
		public int getY() {
			return point.y;
		}
		
		public void setY(int y) {
			point.y = y;
		}
		
		public Point getCoordinate() {
			return new Point(point);
		}
	}
	
	public static class Text {
		private String string;
		private String fontName;
		private BigDecimal fontSize;
		
		public Text(String string, String fontName, BigDecimal fontSize) {
			this.string = string;
			this.fontName = fontName;
			this.fontSize = fontSize;
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public String getFontName() {
			return fontName;
		}

		public void setFontName(String fontName) {
			this.fontName = fontName;
		}

		public BigDecimal getFontSize() {
			return fontSize;
		}

		public void setFontSize(BigDecimal fontSize) {
			this.fontSize = fontSize;
		}
	}
	
	public static class Image {
		private String fileName;
		private String fileData;
		private Dimension size;
		
		public Image(String fileName, String fileData) {
			this.fileName = fileName;
			this.fileData = fileData;
			size = new Dimension();
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileData() {
			return fileData;
		}

		public void setFileData(String fileData) {
			this.fileData = fileData;
		}
		
		public int getWidth() {
			return size.width;
		}
		
		public void setWidth(int width) {
			size.width = width;
		}
		
		public int getHeight() {
			return size.height;
		}
		
		public void setHeight(int height) {
			size.height = height;
		}
		
		public Dimension getSize() {
			return new Dimension(size);
		}
	}
}
