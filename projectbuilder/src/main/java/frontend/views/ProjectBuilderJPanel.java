package frontend.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

import frontend.beans.ProjectSetup;
import logic.ProjectSetupService;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Font;

public class ProjectBuilderJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BindingGroup m_bindingGroup;
	private frontend.beans.ProjectSetup projectSetup = new frontend.beans.ProjectSetup();
	private JTextField currentProjectNameJTextField;
	private JTextField newProjectNameJTextField;
	private JTextField projectPathJTextField;
	private JFileChooser fileChooser;
	private JButton button;
	private JPanel panel;
	private JButton btnOk;
	private JButton btnAbbrechen;
	private ProjectSetupService projectSetupService = new ProjectSetupService();
	
	Logger LOG = Logger.getLogger(ProjectBuilderJPanel.class);
	private JPanel panel_1;

	public ProjectBuilderJPanel(frontend.beans.ProjectSetup newProjectSetup) {
		this();
		setProjectSetup(newProjectSetup);
	}
	

	public ProjectBuilderJPanel() {
		setBackground(SystemColor.inactiveCaptionBorder);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 303, 75, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0E-4 };
		setLayout(gridBagLayout);

		JLabel packageNameLabel = new JLabel("Current Name:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.insets = new Insets(5, 5, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 0;
		add(packageNameLabel, labelGbc_0);

		currentProjectNameJTextField = new JTextField();
		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.insets = new Insets(5, 0, 5, 5);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 0;
		add(currentProjectNameJTextField, componentGbc_0);

		JLabel projectNameLabel = new JLabel("New Name:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.insets = new Insets(5, 5, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 1;
		add(projectNameLabel, labelGbc_1);

		newProjectNameJTextField = new JTextField();
		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.insets = new Insets(5, 0, 5, 5);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 1;
		add(newProjectNameJTextField, componentGbc_1);

		JLabel projectPathLabel = new JLabel("Project Path:");
		GridBagConstraints labelGbc_2 = new GridBagConstraints();
		labelGbc_2.insets = new Insets(5, 5, 5, 5);
		labelGbc_2.gridx = 0;
		labelGbc_2.gridy = 2;
		add(projectPathLabel, labelGbc_2);

		projectPathJTextField = new JTextField();
		GridBagConstraints componentGbc_2 = new GridBagConstraints();
		componentGbc_2.anchor = GridBagConstraints.NORTH;
		componentGbc_2.insets = new Insets(5, 0, 5, 5);
		componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_2.gridx = 1;
		componentGbc_2.gridy = 2;
		add(projectPathJTextField, componentGbc_2);
		
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Select Directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		
		button = new JButton("");
		button.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button.setSelectedIcon(null);
		button.setIcon(new ImageIcon(ProjectBuilderJPanel.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		button.setBackground(UIManager.getColor("Button.background"));
		panel_1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile(projectPathJTextField);
			}
		});
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_bindingGroup.bind();
				projectSetupService.renameAll(projectSetup);
			}
		});
		panel.add(btnOk);
		
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnAbbrechen);

		if (projectSetup != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	public frontend.beans.ProjectSetup getProjectSetup() {
		return projectSetup;
	}

	public void setProjectSetup(frontend.beans.ProjectSetup newProjectSetup) {
		setProjectSetup(newProjectSetup, true);
	}

	public void setProjectSetup(frontend.beans.ProjectSetup newProjectSetup, boolean update) {
		projectSetup = newProjectSetup;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (projectSetup != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}
	
	private void chooseFile(JTextField jTextField) {
		fileChooser.setSelectedFile(new File(jTextField.getText()));
		int returnVal = fileChooser.showOpenDialog(jTextField);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	jTextField.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
            try {
              // return the file path 
            } catch (Exception ex) {
            	LOG.error("Problem accessing file.");
            }
        } 
        else {
        	LOG.info("File access cancelled by user.");
        }
	}   
	protected BindingGroup initDataBindings() {
		BeanProperty<ProjectSetup, String> packageNameProperty = BeanProperty.create("currentProjectName");
		BeanProperty<JTextField, String> textProperty = BeanProperty.create("text");
		AutoBinding<ProjectSetup, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, projectSetup, packageNameProperty, currentProjectNameJTextField, textProperty);
		autoBinding.bind();
		//
		BeanProperty<ProjectSetup, String> projectNameProperty = BeanProperty.create("newProjectName");
		BeanProperty<JTextField, String> textProperty_1 = BeanProperty.create("text");
		AutoBinding<ProjectSetup, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, projectSetup, projectNameProperty, newProjectNameJTextField, textProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<ProjectSetup, String> projectPathProperty = BeanProperty.create("projectPath");
		BeanProperty<JTextField, String> textProperty_2 = BeanProperty.create("text");
		AutoBinding<ProjectSetup, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, projectSetup, projectPathProperty, projectPathJTextField, textProperty_2);
		autoBinding_2.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		return bindingGroup;
	}
}