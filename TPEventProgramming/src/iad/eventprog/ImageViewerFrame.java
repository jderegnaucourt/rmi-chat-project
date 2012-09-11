package iad.eventprog;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.*;


public class ImageViewerFrame extends JFrame implements ActionListener, KeyListener, ImageChangedListener {

	JLabel imageName;
	JTextField pathName; 
	JButton previous;
	JButton next;
	ImageViewerBean imageViewer;
	
	ImageViewerFrame() {
		
		String initPath = "D:\\Profils\\jderegnaucourt\\Desktop";	
		imageViewer = new ImageViewerBean(initPath);
		imageViewer.addListener(this);
		imageViewer.addListener(new ImageChangedListener() {

			@Override
			public void ImageChangePerformed(ImageChangedEvent e) {
				System.out.println("new ImageChangePerformed for ImageChangedEvent for : "+e._nameOfNewImage);
			}
			
		});
		
		add(imageViewer, BorderLayout.CENTER);
		
		JPanel tfPanel = new JPanel(new BorderLayout());	
		
		pathName = new JTextField(initPath);
		pathName.addKeyListener(this);
		tfPanel.add(pathName, BorderLayout.NORTH);
		
		imageName = new JLabel(" ");
		imageName.setBorder(BorderFactory.createLineBorder(Color.RED));
		tfPanel.add(imageName,BorderLayout.SOUTH);

		add(tfPanel, BorderLayout.NORTH);
		
		
		JPanel btnPanel = new JPanel();
		previous = new JButton("<-");
		previous.addActionListener(this);
		btnPanel.add(previous);
		
		next = new JButton("->");
		next.addActionListener(this);
		btnPanel.add(next);
			
		add(btnPanel, BorderLayout.SOUTH);
		setSize(600, 400);
		
		imageViewer.first();
	}
	
	@Override
	public void actionPerformed(ActionEvent buttonAction) {
		if(buttonAction.getSource() == previous) imageViewer.previous();
		else if(buttonAction.getSource() == next) imageViewer.next();
	}
	
	public static void main(String args[]) {
		ImageViewerFrame frame = new ImageViewerFrame();
		frame.setVisible(true);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed(KeyEvent e)");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
			System.out.println("key was 'enter'");
			imageViewer.setPathName(pathName.getText());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ImageChangePerformed(ImageChangedEvent e) {
		imageName.setText(e.getNameOfNewImage());
	}

}
