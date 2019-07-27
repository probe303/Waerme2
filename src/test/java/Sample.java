import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.*;

import net.ericaro.surfaceplotter.DefaultSurfaceModel;
import net.ericaro.surfaceplotter.JSurfacePanel;
import net.ericaro.surfaceplotter.Mapper;
import net.ericaro.surfaceplotter.ProgressiveSurfaceModel;
import net.ericaro.surfaceplotter.surface.SurfaceUtils;




/**
 * @author User #1
 */
public class Sample extends JPanel {
	private ProgressiveSurfaceModel model;
	Fundamental funda=new Fundamental();
	u0 u=new u0();
	public Sample() {
		initComponents();
		
		//DefaultSurfaceModel model = new DefaultSurfaceModel();
		model = new ProgressiveSurfaceModel() ;
		surfacePanel1.setModel(model);

		model.setMapper(new Mapper() {
			public  float f1( float x, float y)
			{
				//if(slider2.getValue()!=0){
					//funda.t=slider2.getValue();
				if(slider2.getScaledValue()!=0){
					funda.t=slider2.getScaledValue();
					return Funktion.fold(funda, u, x, y);
				}else{
					return  u.eval(x,y);
				}
			}
			
			public  float f2( float x, float y)
			{
				return (float)(Math.sin(x*y));
			}
		});
		model.plot().execute();

		slider1.setValue(6);
	}

	private void button1ActionPerformed()  {
		JFileChooser jfc = new JFileChooser();
		try {
			if (jfc.showSaveDialog(surfacePanel1) == JFileChooser.APPROVE_OPTION )
				SurfaceUtils.doExportSVG(surfacePanel1.getSurface(), jfc.getSelectedFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Sample sample = new Sample();
		JFrame jf = new JFrame("Wärmeleitungsgleichung");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().add(sample);
		jf.pack();
		jf.setVisible(true);
		jf.setSize(1024,768);
	}

	private void slider1StateChanged(ChangeEvent e) {
		if (!slider1.getValueIsAdjusting())
			model.setCurrentDefinition(slider1.getValue());
	}

	private void slider2StateChanged(ChangeEvent e) {/**/
		//surfacePanel1.setModel(model);
		model.setMapper(new Mapper() {
			public  float f1( float x, float y)
			{
				//if(slider2.getValue()!=0){
					//funda.t=slider2.getValue();
				if(slider2.getScaledValue()!=0){
					funda.t=slider2.getScaledValue();
					return Funktion.fold(funda, u, x, y);
				}else{
					return  u.eval(x,y);
				}
			}

			public  float f2( float x, float y)
			{
				return (float)(Math.sin(x*y));
			}
		});
		//if (!slider1.getValueIsAdjusting())
			//model.setCurrentDefinition(slider1.getValue());
		model.plot().execute();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		surfacePanel1 = new JSurfacePanel();
		toolBar1 = new JToolBar();
		button1 = new JButton();
		slider1 = new JSlider();
		//slider2 = new JSlider();

		//======== this ========
		setLayout(new BorderLayout());

		//---- surfacePanel1 ----
		surfacePanel1.setTitleText("sin(r)/r");
		surfacePanel1.setBackground(Color.white);
		surfacePanel1.setTitleFont(surfacePanel1.getTitleFont().deriveFont(surfacePanel1.getTitleFont().getStyle() | Font.BOLD, surfacePanel1.getTitleFont().getSize() + 6f));
		surfacePanel1.setConfigurationVisible(false);
		add(surfacePanel1, BorderLayout.CENTER);

		//======== toolBar1 ========
		{

			//---- button1 ----
			button1.setText("export SVG");
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button1ActionPerformed();
				}
			});
			toolBar1.add(button1);

			//---- slider1 ----
			slider1.setMaximum(6);
			slider1.setValue(0);
			slider1.setPaintTicks(true);
			slider1.setSnapToTicks(true);
			slider1.setMinorTickSpacing(1);
			slider1.setMajorTickSpacing(1);
			slider1.setPaintLabels(true);
			slider1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					slider1StateChanged(e);
				}
			});
			toolBar1.add(slider1);

			//---- slider2 ----
			slider2 = new DoubleJSlider(0, 10, 0, 10);
			//slider2.setMaximum(10);
			//slider2.setValue(0);
			slider2.setPaintTicks(true);
			slider2.setSnapToTicks(true);
			slider2.setMinorTickSpacing(1);
			slider2.setMajorTickSpacing(1);
			slider2.setPaintLabels(true);
			slider2.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					slider2StateChanged(e);
				}
			});
			toolBar1.add(slider2);
		}
		add(toolBar1, BorderLayout.NORTH);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JSurfacePanel surfacePanel1;
	private JToolBar toolBar1;
	private JButton button1;
	private JSlider slider1;
	//private JSlider slider2;
	private DoubleJSlider slider2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}

class DoubleJSlider extends JSlider {

	final int scale;

	public DoubleJSlider(int min, int max, int value, int scale) {
		super(min, max, value);
		this.scale = scale;
	}

	public float getScaledValue() {
		return ((float)super.getValue()) / this.scale;
	}
}
