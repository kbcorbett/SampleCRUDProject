import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Image;
import java.sql.*;
import java.text.ParseException;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;


/* Authored by Kristoffer Corbett
 * Version 1.0 written 10/4/2018
 * Revision comments will include date of revision
*/
public class HomeScreen {
	private static Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	//global variable holds status of last database connection attempt
	static boolean goodConnect;
	private static Text abilityName;
	private static Text abilityDesc;
	private static Text abilityID;
	private static Text statsName;
	private static Text statsID;
	private static Text weaponName;
	private static Text weaponID;
	private static Text weaponPoints;
	private static Text unitID;
	private static Text unitModel;
	private static Text unitPoints;
	
	public static void main(String[] args) {
		
		//create new fixed size display window for showing program tabs
		Display tabDisplay = new Display();
	    final Shell tabShell = new Shell(tabDisplay, SWT.SHELL_TRIM & (~SWT.RESIZE));
	    tabShell.setText("Warhammer Model Viewer");
	    tabShell.setSize(1000, 450);
	    tabShell.setLayout(null);
	    
	    //create tab folder
	    final TabFolder tabFolder = new TabFolder(tabShell, SWT.BORDER);
	    tabFolder.setBounds(0, 0, 970, 209);

	    //create view models tab
	    TabItem viewModels = new TabItem(tabFolder, SWT.NULL);
	    viewModels.setText("View Models");
	    
	    //create composite in view models tab for loading multiple button types
	    Composite viewComposite = new Composite(tabFolder, SWT.NONE);
	    viewModels.setControl(viewComposite);	    
	      
	    //create combo box in view models tab for displaying models in database
	    Combo selectModel = new Combo(viewComposite, SWT.READ_ONLY);
	    selectModel.setBounds(10, 10, 402, 23);
	    
	    //create output table and column names
	    table = new Table(tabShell, SWT.BORDER | SWT.FULL_SELECTION);
	    table.setBounds(0, 215, 970, 177);
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    
	    TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn.setWidth(35);
	    tblclmnNewColumn.setText("ID");
	    
	    TableColumn tblclmnNewColumn_0 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_0.setWidth(100);
	    tblclmnNewColumn_0.setText("Name");
	    
	    //stats name column added 11/16/2018
	    TableColumn tblclmnNewColumn_19 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_19.setWidth(100);
	    tblclmnNewColumn_19.setText("Stats Name");
	    
	    TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_1.setWidth(35);
	    tblclmnNewColumn_1.setText("WS");
	    
	    TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_2.setWidth(35);
	    tblclmnNewColumn_2.setText("BS");
	    
	    TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_3.setWidth(25);
	    tblclmnNewColumn_3.setText("S");
	    
	    TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_4.setWidth(25);
	    tblclmnNewColumn_4.setText("T");
	    
	    TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_5.setWidth(25);
	    tblclmnNewColumn_5.setText("W");
	    
	    TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_6.setWidth(35);
	    tblclmnNewColumn_6.setText("Sv");
	    
	    TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_7.setWidth(100);
	    tblclmnNewColumn_7.setText("Weapon Name");
	    
	    TableColumn tblclmnNewColumn_8 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_8.setWidth(50);
	    tblclmnNewColumn_8.setText("Range");
	    
	    TableColumn tblclmnNewColumn_9 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_9.setWidth(50);
	    tblclmnNewColumn_9.setText("Type");
	    
	    TableColumn tblclmnNewColumn_10 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_10.setWidth(50);
	    tblclmnNewColumn_10.setText("W. Str");
	    
	    TableColumn tblclmnNewColumn_11 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_11.setWidth(50);
	    tblclmnNewColumn_11.setText("AP");
	    
	    TableColumn tblclmnNewColumn_12 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_12.setWidth(50);
	    tblclmnNewColumn_12.setText("Damage");
	    
	    TableColumn tblclmnNewColumn_13 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_13.setWidth(100);
	    tblclmnNewColumn_13.setText("Ability Name");
	    
	    TableColumn tblclmnNewColumn_14 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_14.setWidth(50);
	    tblclmnNewColumn_14.setText("Radius");
	    
	    TableColumn tblclmnNewColumn_15 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_15.setWidth(150);
	    tblclmnNewColumn_15.setText("Ability Description");
	    
	    TableColumn tblclmnNewColumn_16 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_16.setWidth(50);
	    tblclmnNewColumn_16.setText("Points");
	    
	    TableColumn tblclmnNewColumn_17 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_17.setWidth(100);
	    tblclmnNewColumn_17.setText("Date added");
	    
	    TableColumn tblclmnNewColumn_18 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_18.setWidth(50);
	    tblclmnNewColumn_18.setText("Caster");
	    //end table creation
	    
	    //create new button in view models tab for displaying models
	    Button displayModel = new Button(viewComposite, SWT.NONE);
	    displayModel.setBounds(830, 131, 100, 25);
	    displayModel.setText("Display Model(s)");
	    
	    //*********************************10/17/2018 ability tab update*************************
	    
	    //create ability tab
	    TabItem abilities = new TabItem(tabFolder, SWT.NONE);
	    abilities.setText("Abilities");
	    
	    //create ability tab composite for holding objects
	    Composite abilityComposite = new Composite(tabFolder, SWT.NONE);
	    abilities.setControl(abilityComposite);
	    
	    //create label and text field for ability name
	    Label nameLbl = new Label(abilityComposite, SWT.NONE);
	    nameLbl.setBounds(10, 10, 55, 15);
	    nameLbl.setText("Name");
	    
	    abilityName = new Text(abilityComposite, SWT.BORDER);
	    abilityName.setBounds(10, 31, 76, 21);
	    
	    //create label and text field for ability description
	    Label descriptionLbl = new Label(abilityComposite, SWT.NONE);
	    descriptionLbl.setBounds(130, 10, 69, 15);
	    descriptionLbl.setText("Description");
	    
	    abilityDesc = new Text(abilityComposite, SWT.BORDER);
	    abilityDesc.setBounds(130, 31, 625, 21);
	    
	    //create label and read only combo box for ability radius
	    Label radiusLbl = new Label(abilityComposite, SWT.NONE);
	    radiusLbl.setBounds(808, 10, 55, 15);
	    radiusLbl.setText("Radius");
	    
	    //all ability radius in game are 0,6,9,12, or 18
	    Combo abilityRadius = new Combo(abilityComposite, SWT.READ_ONLY);
	    abilityRadius.setItems(new String[] {"0", "6", "9", "12", "18"});
	    abilityRadius.setBounds(808, 31, 91, 23);
	    
	    //create label and text box for ability ID
	    Label abilityIDlabel = new Label(abilityComposite, SWT.NONE);
	    abilityIDlabel.setBounds(10, 58, 55, 15);
	    abilityIDlabel.setText("Ability ID");
	    
	    abilityID = new Text(abilityComposite, SWT.BORDER);
	    abilityID.setBounds(10, 79, 76, 21);
	    
	    //abilityIDcheck label is used to display error on bad input
	    Label abilityIDcheck = new Label(abilityComposite, SWT.NONE);
	    abilityIDcheck.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    abilityIDcheck.setBounds(10, 106, 453, 15);
	    
	    //this button is used to add or update abilities in the database
	    Button abilityAdd = new Button(abilityComposite, SWT.NONE);
	    abilityAdd.setBounds(10, 142, 75, 25);
	    abilityAdd.setText("Add/Update");
	    abilityAdd.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		/*the following variables are used to create the add/update sql string
	    		 * variables are initialized to default values which are replaced if there is user input
	    		 * abilityIDinput is checked for validity later and provides an error message to user
	    		 */
	    		String name = "no name";
	    		if (abilityName.getText() != "")
	    			name = abilityName.getText();
	    		String desc = "no description";
	    		if (abilityDesc.getText() != "")
	    			desc = abilityDesc.getText();
	    		String radius = "0";
	    		if (abilityRadius.getText() != "")
	    			radius = abilityRadius.getText();
	    		String abilityIDinput = abilityID.getText();
	    		String strAddUpdate = "";
	    		boolean goodInt = false;
	    		
	    		//tracks if this is an add or update action
	    		boolean abilityUpdate = false;
	    		
	    		/*this try statement checks if the ability ID is a valid integer by attempting to parse it
	    		 * if successful the boolean goodInt allows the following IF statement to fire proceeding with the add/update
	    		 * if the try is unsuccessful an error message is presented to the user
	    		*/
	    		try 
	    		{
    				if (Integer.parseInt(abilityIDinput) >= 0)
    					goodInt = true;
    				else
    	    			abilityIDcheck.setText("You must enter a valid Integer");
	    		}
	    		catch (NumberFormatException ex)
	    		{
	    			abilityIDcheck.setText("You must enter a valid Integer");
	    		}
	    		
	    		//check if ability ID was a valid int
	    		if (goodInt)
	    		{
	    			//get updated ability list from database
		    	    String[][] units = updateOther("ability");
		    	    //iterate through all abilities, set update to true if abilityIDinput(the user input ability ID) exists in the the database
		    	    for (int i=0 ; i < units.length ; i++)
		    	    {
		    	    	//these are safe parses as both values are tested in other areas
		    	    	if (Integer.parseInt(units[i][0]) == Integer.parseInt(abilityIDinput))
		    	    	{
		    	    		abilityUpdate = true;
		    	    		if (abilityName.getText() == "")
		    	    			name = units[i][16];
		    	    		if (abilityDesc.getText() == "")
		    	    			desc = units[i][17];
		    	    		if (abilityRadius.getText() == "")
		    	    			radius = units[i][18];
		    	    		
		    	    	}
		    	    }
		    	    
		    	    //creates correct add/update statement base on previous test
	    			if(abilityUpdate)
	    			{
	    				
	    				//create SQL update statement
	    				strAddUpdate = "update ability set name = '" + name + "' , description = '" + desc + "' , radius = " + radius + " where abilityid = " + abilityIDinput;
	    			}
	    			else
	    			{
	    				//create SQL add statement
	    				strAddUpdate = "insert into ability values(" + abilityIDinput + ", " + "'" + name + "'" + ", " + "'" + desc + "'" + ", " + radius + ")";
	    			}
	    			
	    			
	    			//this try connects to the database and executes the add/update statement
	    			try (
	    					//connect to database hosted locally
	    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
	    					Statement stmt = conn.createStatement();
	    					)
	    			{
	    				//execute the add/update statement
	    				stmt.execute(strAddUpdate);
	    				if(abilityUpdate) //checks for update or add to provide correct user feedback  - added 11/8/2018(user feedback update)
	    					abilityIDcheck.setText("ability #" + abilityIDinput + " sucessfully updated.");
	    				else
	    					abilityIDcheck.setText("ID #" + abilityIDinput + " sucessfully added as a new ability.");
	    			}
	    			catch (SQLException ex)
	    			{
	    				System.out.println(ex);
	    			}
	    
	    		}
	    	}
	        	public void widgetDefaultSelected(SelectionEvent event)
	        	{
	        	}
	        });
	    
	    //delete button error label
	    Label abilityDeleteLbl = new Label(abilityComposite, SWT.NONE);
	    abilityDeleteLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    abilityDeleteLbl.setBounds(211, 152, 434, 15);
	    
	    //this button displays all abilities in the output window
	    Button abilityView = new Button(abilityComposite, SWT.NONE);
	    abilityView.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		
	    		//clear error message
	    		abilityIDcheck.setText("");
	    		abilityDeleteLbl.setText("");
	    	
	    		//obtain up to date ability info from database and store locally
	    		String[][] units = updateOther("ability");
	    		//clear output window
	    		table.removeAll();
	    		
	    		//iterate through array to display all abilities
	    		for (int i=0 ; i < units.length ; i++)
	    		{
	    			TableItem item = new TableItem(table, SWT.NONE);
	    			item.setText(new String[] {units[i][0],null,null,null,null,null,null,null,null,null,null,null,null,null,null,units[i][16], units[i][18], units[i][17]});
	    		}
	    	}
	        public void widgetDefaultSelected(SelectionEvent event)
	        {                    
	        }
	    });
	    
	    //view ability button bounds and tag
	    abilityView.setBounds(851, 142, 97, 25);
	    abilityView.setText("View all Abilities");
	    
	    //Ability delete button
	    Button abilityDelete = new Button(abilityComposite, SWT.NONE);
	    abilityDelete.setBounds(130, 142, 75, 25);
	    abilityDelete.setText("Delete");
	    abilityDelete.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		//store user entered ability ID
	    		String abilityIDinput = abilityID.getText();
	    		
	    		//collect updated ability info - added 11/8/2018(user feedback update)
	    		String[][] units = updateOther("ability");
	    		
	    		//check if the user input exists in the system  - added 11/8/2018(user feedback update)
	    		boolean inArray = false;
	    		for (int i = 0 ; i < units.length ; i++)
	    		{
	    			if ( units[i][0].equals(abilityIDinput))
	    				inArray = true;				
	    		}
	    		
	    		//proceed only if the provided ID exists - added 11/8/2018(user feedback update)
	    		if ( inArray == false)
	    			abilityDeleteLbl.setText("Ability #" + abilityIDinput + " does not exist. delete failed.");
	    		else
	    		{
	    			try (
    					//connect to database hosted locally
    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
    					Statement stmt = conn.createStatement();
    					)
	    			{
	    				//execute the delete statement
	    				stmt.execute("delete from ability where abilityid = " + abilityIDinput);
	    				abilityDeleteLbl.setText("Ability #" + abilityIDinput + " successfully deleted");
	    			}
	    		
	    			//if ability to be deleted is in use on a unit this error handling will inform use that the ability cannot be deleted
	    			catch (SQLIntegrityConstraintViolationException ex)
	    			{
	    				abilityDeleteLbl.setText("Abilities cannot be deleted while in use by a unit");
	    			}
	    		
	    			//remind user of acceptable ID's to use
	    			catch (SQLException ex)
	    			{
	    				abilityDeleteLbl.setText("You must enter an existing ID number in integer format");
	    			}
	    			}
	    		}
	        	public void widgetDefaultSelected(SelectionEvent event)
	        	{
	        	}
	        });
	    
	    //*****************************end ability update*******************************
	    
	    
		/*****************************added 10/11/2018********************************
		 * Image will be displayed on program launch if initial database connection was successful
		 * Connection failure results in an error message displayed in place of image
		 */
	    
	    //label to contain image or error message
	    CLabel errorLogo = new CLabel(viewComposite, SWT.WRAP);
	    errorLogo.setAlignment(SWT.CENTER);
	    errorLogo.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    errorLogo.setBounds(533, 10, 415, 64);
	    
	    //connect to database using update method and fill unit array(this will also set a value to the  "goodConnect" global variable)
	    String[][] units = updateAll();
	    
	    //fill combo box with units
		selectModel.removeAll();
	    selectModel.setText("Select Model to View");
	    selectModel.add("View all Models");
	    
	    //**************************************Update 10/24/2018 Stats Tab added************************************************
	    TabItem stats = new TabItem(tabFolder, SWT.NONE);
	    stats.setText("Stats");
	    
	    Composite statsComposite = new Composite(tabFolder, SWT.NONE);
	    stats.setControl(statsComposite);
	    
	    statsName = new Text(statsComposite, SWT.BORDER);
	    statsName.setBounds(10, 31, 76, 21);
	    
	    statsID = new Text(statsComposite, SWT.BORDER);
	    statsID.setBounds(10, 79, 76, 21);
	    
	    Label lblName = new Label(statsComposite, SWT.NONE);
	    lblName.setBounds(10, 10, 55, 15);
	    lblName.setText("Name");
	    
	    Label lblStatsId = new Label(statsComposite, SWT.NONE);
	    lblStatsId.setBounds(10, 58, 55, 15);
	    lblStatsId.setText("Stats ID");
	    
	    Label lblNewLabel = new Label(statsComposite, SWT.NONE);
	    lblNewLabel.setBounds(117, 10, 88, 15);
	    lblNewLabel.setText("Weapon Skill");
	    
	    Combo statsWS = new Combo(statsComposite, SWT.NONE);
	    statsWS.setItems(new String[] {"0.17", "0.33", "0.50", "0.66", "0.83"});
	    statsWS.setBounds(114, 31, 91, 23);
	    
	    Label lblNewLabel_1 = new Label(statsComposite, SWT.NONE);
	    lblNewLabel_1.setBounds(117, 58, 88, 15);
	    lblNewLabel_1.setText("Ballistic Skill");
	    
	    Combo statsBS = new Combo(statsComposite, SWT.NONE);
	    statsBS.setItems(new String[] {"0.17", "0.33", "0.50", "0.66", "0.83"});
	    statsBS.setBounds(114, 79, 91, 23);
	    
	    Label lblNewLabel_2 = new Label(statsComposite, SWT.NONE);
	    lblNewLabel_2.setBounds(253, 10, 91, 15);
	    lblNewLabel_2.setText("Strength");
	    
	    Combo statsStrength = new Combo(statsComposite, SWT.NONE);
	    statsStrength.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
	    statsStrength.setBounds(253, 31, 91, 23);
	    
	    Label lblNewLabel_3 = new Label(statsComposite, SWT.NONE);
	    lblNewLabel_3.setBounds(253, 58, 91, 15);
	    lblNewLabel_3.setText("Toughness");
	    
	    Combo statsToughness = new Combo(statsComposite, SWT.NONE);
	    statsToughness.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
	    statsToughness.setBounds(253, 79, 91, 23);
	    
	    Label lblNewLabel_4 = new Label(statsComposite, SWT.NONE);
	    lblNewLabel_4.setBounds(382, 10, 55, 15);
	    lblNewLabel_4.setText("Wounds");
	    
	    Combo statsWounds = new Combo(statsComposite, SWT.NONE);
	    statsWounds.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"});
	    statsWounds.setBounds(382, 29, 91, 23);
	    
	    Label lblNewLabel_5 = new Label(statsComposite, SWT.NONE);
	    lblNewLabel_5.setBounds(382, 58, 55, 15);
	    lblNewLabel_5.setText("Save");
	    
	    Combo statsSave = new Combo(statsComposite, SWT.NONE);
	    statsSave.setItems(new String[] {"0.17", "0.33", "0.50", "0.66", "0.83"});
	    statsSave.setBounds(382, 79, 91, 23);
	    
	    Label statsIDcheck = new Label(statsComposite, SWT.NONE);
	    statsIDcheck.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    statsIDcheck.setBounds(10, 106, 463, 15);
	    
	    Button statsAdd = new Button(statsComposite, SWT.NONE);
	    statsAdd.setBounds(10, 142, 75, 25);
	    statsAdd.setText("Add/Update");
	    statsAdd.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		/*the following variables are used to create the add/update sql string
	    		 * variables are initialized to default values which are replaced if there is user input
	    		 * statsIDinput is checked for validity later and provides an error message to user
	    		 */
	    		String name = "no name";
	    		if (statsName.getText() != "")
	    			name = statsName.getText();
	    		
	    		String ws = "0";
	    		if (statsWS.getText() != "")
	    			ws = statsWS.getText();
	    		
	    		String bs = "0";
	    		if (statsBS.getText() != "")
	    			bs = statsBS.getText();
	    		
	    		String strength = "0";
	    		if (statsStrength.getText() != "")
	    			strength = statsStrength.getText();
	    		
	    		String toughness = "0";
	    		if (statsToughness.getText() != "")
	    			toughness = statsToughness.getText();
	    		
	    		String wounds = "0";
	    		if (statsWounds.getText() != "")
	    			wounds = statsWounds.getText();
	    		
	    		String save = "0";
	    		if (statsSave.getText() != "")
	    			save = statsSave.getText();
	    		
	    		String statsIDinput = statsID.getText();
	    		String strAddUpdate = "";
	    		boolean goodInt = false;
	    		
	    		//tracks if this is an add or update action
	    		boolean statsUpdate = false;
	    		
	    		/*this try statement checks if the stats ID is a valid integer by attempting to parse it
	    		 * if successful the boolean goodInt allows the following IF statement to fire proceeding with the add/update
	    		 * if the try is unsuccessful an error message is presented to the user
	    		*/
	    		try 
	    		{
    				if (Integer.parseInt(statsIDinput) >= 0)
    					goodInt = true;
    				else
    	    			statsIDcheck.setText("You must enter a valid Integer");
	    		}
	    		catch (NumberFormatException ex)
	    		{
	    			statsIDcheck.setText("You must enter a valid Integer");
	    		}
	    		
	    		//check if stats ID was a valid int
	    		if (goodInt)
	    		{
	    			//get updated stats list from database
		    	    String[][] units = updateOther("stats");
		    	    //iterate through all stats, set update to true if statsIDinput(the user input stats ID) exists in the the database
		    	    for (int i=0 ; i < units.length ; i++)
		    	    {
		    	    	//these are safe parses as both values are tested in other areas
		    	    	if (Integer.parseInt(units[i][0]) == Integer.parseInt(statsIDinput))
		    	    	{
		    	    		statsUpdate = true;
		    	    		if (statsName.getText() == "")
		    	    			name = units[i][1];
		    	    		if (statsWS.getText() == "")
		    	    			ws = units[i][2];
		    	    		if (statsBS.getText() == "")
		    	    			bs = units[i][3];
		    	    		if (statsStrength.getText() == "")
		    	    			strength = units[i][4];
		    	    		if (statsToughness.getText() == "")
		    	    			toughness = units[i][5];
		    	    		if (statsWounds.getText() == "")
		    	    			wounds = units[i][6];
		    	    		if (statsSave.getText() == "")
		    	    			save = units[i][7];
		    	    		
		    	    	}
		    	    }
		    	    
		    	    //creates correct add/update statement base on previous test
	    			if(statsUpdate)
	    			{
	    				
	    				//create SQL update statement
	    				strAddUpdate = "update stats set name = '" + name + "' , ws = " + ws + " , bs = " + bs + " , strength = " + strength + 
	    						" , toughness = " + toughness + " , wounds = " + wounds + " , save = " + save + " where statsid = " + statsIDinput;
	    			}
	    			else
	    			{
	    				//create SQL add statement
	    				strAddUpdate = "insert into stats values(" + statsIDinput + ", " + "'" + name + "'" + ", " + "" + ws + "" + ", " + bs + 
	    						", " + "" + strength + "" + ", " + "" + toughness + "" + ", " + wounds + "" + ", " + save + ")";
	    			}
	    			
	    			
	    			//this try connects to the database and executes the add/update statement
	    			try (
	    					//connect to database hosted locally
	    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
	    					Statement stmt = conn.createStatement();
	    					)
	    			{
	    				//execute the add/update statement
	    				stmt.execute(strAddUpdate);
	    				if(statsUpdate) //checks for update or add to provide correct user feedback  - added 11/8/2018(user feedback update)
	    					statsIDcheck.setText("stats #" + statsIDinput + " sucessfully updated.");
	    				else
	    					statsIDcheck.setText("ID #" + statsIDinput + " sucessfully added as a new stat line.");
	    			}
	    			catch (SQLException ex)
	    			{
	    				System.out.println(ex);
	    			}
	    
	    		}
	    	}
	        	public void widgetDefaultSelected(SelectionEvent event)
	        	{
	        	}
	        });
	    
	    Label statsDeleteLbl = new Label(statsComposite, SWT.NONE);
	    statsDeleteLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    statsDeleteLbl.setBounds(198, 152, 412, 15);
	    
	    Button statsDelete = new Button(statsComposite, SWT.NONE);
	    statsDelete.setBounds(117, 142, 75, 25);
	    statsDelete.setText("Delete");
	    statsDelete.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		//store user entered stats ID
	    		String statsIDinput = statsID.getText();
	    		
	    		//collect updated ability info - added 11/8/2018(user feedback update)
	    		String[][] units = updateOther("stats");
	    		
	    		//check if the user input exists in the system  - added 11/8/2018(user feedback update)
	    		boolean inArray = false;
	    		for (int i = 0 ; i < units.length ; i++)
	    		{
	    			if ( units[i][0].equals(statsIDinput))
	    				inArray = true;				
	    		}
	    		
	    		//proceed only if the provided ID exists - added 11/8/2018(user feedback update)
	    		if ( inArray == false)
	    			statsDeleteLbl.setText("Stats #" + statsIDinput + " does not exist. delete failed.");
	    		else
	    		{
	    			try (
	    					//connect to database hosted locally
	    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
	    					Statement stmt = conn.createStatement();
	    				)
	    			{
	    				//execute the delete statement
	    				stmt.execute("delete from stats where statsid = " + statsIDinput);
	    				statsDeleteLbl.setText("Stats #" + statsIDinput + " successfully deleted"); //added 11/8/2018(user feedback update)
	    			}
	    		
	    			//if stats to be deleted is in use on a unit this error handling will inform use that the ability cannot be deleted
	    			catch (SQLIntegrityConstraintViolationException ex)
	    			{
	    				statsDeleteLbl.setText("Stats cannot be deleted while in use by a unit");
	    			}
	    		
	    			//remind user of acceptable ID's to use
	    			catch (SQLException ex)
	    			{
	    				statsDeleteLbl.setText("You must enter an existing ID number in integer format");
	    			}
	    		}
	    	}
	        	public void widgetDefaultSelected(SelectionEvent event)
	        	{
	        	}
	        });
	    
	    
	    Button statsView = new Button(statsComposite, SWT.NONE);
	    statsView.setBounds(851, 142, 97, 25);
	    statsView.setText("View all stats");
	    statsView.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		
	    		//clear error message
	    		statsIDcheck.setText("");
	    		statsDeleteLbl.setText("");
	    	
	    		//obtain up to date stats info from database and store locally
	    		String[][] units = updateOther("stats");
	    		//clear output window
	    		table.removeAll();
	    		
	    		//iterate through array to display all stats
	    		for (int i=0 ; i < units.length ; i++)
	    		{
	    			TableItem item = new TableItem(table, SWT.NONE);
	    			item.setText(new String[] {units[i][0],null,units[i][1],units[i][2],units[i][3],units[i][4],units[i][5],units[i][6],units[i][7]});
	    		}
	    	}
	        public void widgetDefaultSelected(SelectionEvent event)
	        {                    
	        }
	    });
	    
		//**************************************End Update 10/24/2018 Stats Tab added********************************************	
	    //**************************************Update 10/25/2018 Weapons Tab added************************************************	
	    
	    TabItem weapons = new TabItem(tabFolder, SWT.NONE);
	    weapons.setText("Weapons");
	    
	    Composite weaponComposite = new Composite(tabFolder, SWT.NONE);
	    weapons.setControl(weaponComposite);
	    
	    Label lblNewLabel_6 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_6.setBounds(10, 10, 55, 15);
	    lblNewLabel_6.setText("Name");
	    
	    Label lblNewLabel_7 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_7.setBounds(117, 10, 55, 15);
	    lblNewLabel_7.setText("Distance");
	    
	    Label lblNewLabel_8 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_8.setBounds(253, 10, 55, 15);
	    lblNewLabel_8.setText("Type");
	    
	    Label lblNewLabel_9 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_9.setBounds(382, 10, 55, 15);
	    lblNewLabel_9.setText("Strength");
	    
	    weaponName = new Text(weaponComposite, SWT.BORDER);
	    weaponName.setBounds(10, 31, 76, 21);
	    
	    Combo weaponDistance = new Combo(weaponComposite, SWT.NONE);
	    weaponDistance.setItems(new String[] {"0", "6", "12", "18", "24", "30", "36", "42", "48", "54", "60"});
	    weaponDistance.setBounds(117, 29, 91, 23);
	    
	    Combo weaponType = new Combo(weaponComposite, SWT.NONE);
	    weaponType.setItems(new String[] {"Melee", "Rapid Fire 1", "Rapid Fire 2", "Assault 1", "Assault 2", "Assault 3", "Assault 4", "Heavy 1", "Heavy 2", "Heavy 3", "Heavy 4"});
	    weaponType.setBounds(253, 31, 91, 23);
	    
	    Combo weaponStrength = new Combo(weaponComposite, SWT.NONE);
	    weaponStrength.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
	    weaponStrength.setBounds(382, 29, 91, 23);
	    
	    Label lblNewLabel_10 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_10.setBounds(10, 58, 76, 15);
	    lblNewLabel_10.setText("Weapon ID");
	    
	    Label lblNewLabel_11 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_11.setBounds(117, 58, 91, 15);
	    lblNewLabel_11.setText("Penetration");
	    
	    Label lblNewLabel_12 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_12.setBounds(253, 60, 55, 15);
	    lblNewLabel_12.setText("Damage");
	    
	    Label lblNewLabel_13 = new Label(weaponComposite, SWT.NONE);
	    lblNewLabel_13.setBounds(382, 58, 55, 15);
	    lblNewLabel_13.setText("Points");
	    
	    weaponID = new Text(weaponComposite, SWT.BORDER);
	    weaponID.setBounds(10, 79, 76, 21);
	    
	    Combo weaponPenetration = new Combo(weaponComposite, SWT.NONE);
	    weaponPenetration.setItems(new String[] {"0", "-1", "-2", "-3", "-4", "-5"});
	    weaponPenetration.setBounds(117, 79, 91, 23);
	    
	    Combo weaponDamage = new Combo(weaponComposite, SWT.NONE);
	    weaponDamage.setItems(new String[] {"1", "2", "3", "4", "5", "6"});
	    weaponDamage.setBounds(253, 81, 91, 23);
	    
	    Label weaponIDcheck = new Label(weaponComposite, SWT.NONE);
	    weaponIDcheck.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    weaponIDcheck.setBounds(10, 106, 463, 15);
	    
	    Button weaponAdd = new Button(weaponComposite, SWT.NONE);
	    weaponAdd.setBounds(11, 142, 75, 25);
	    weaponAdd.setText("Add/Update");
	    
	    Button weaponDelete = new Button(weaponComposite, SWT.NONE);
	    weaponDelete.setBounds(119, 142, 75, 25);
	    weaponDelete.setText("Delete");
	    
	    Button weaponView = new Button(weaponComposite, SWT.NONE);
	    weaponView.setBounds(836, 142, 112, 25);
	    weaponView.setText("View All Weapons");
	    
	    Label weaponDeleteLbl = new Label(weaponComposite, SWT.NONE);
	    weaponDeleteLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    weaponDeleteLbl.setBounds(200, 152, 414, 15);
	    
	    weaponPoints = new Text(weaponComposite, SWT.BORDER);
	    weaponPoints.setBounds(382, 81, 91, 21);
	    
	    Label weaponPointsCheck = new Label(weaponComposite, SWT.NONE);
	    weaponPointsCheck.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    weaponPointsCheck.setBounds(479, 85, 258, 15);
	    
	    weaponView.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		
	    		//clear error message
	    		weaponIDcheck.setText("");
	    		weaponDeleteLbl.setText("");
	    		weaponPointsCheck.setText("");
	    	
	    		//obtain up to date weapon info from database and store locally
	    		String[][] units = updateOther("weapon");
	    		//clear output window
	    		table.removeAll();
	    		
	    		//iterate through array to display all weapons
	    		for (int i=0 ; i < units.length ; i++)
	    		{
	    			TableItem item = new TableItem(table, SWT.NONE);
	    			item.setText(new String[] {units[i][0],null,null,null,null,null,null,null,null,units[i][8],units[i][9],units[i][10],units[i][11],units[i][12],units[i][13],null,null,null,units[i][17]});
	    		}
	    	}
	        public void widgetDefaultSelected(SelectionEvent event)
	        {                    
	        }
	    });
	    
	    weaponDelete.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		//store user entered weapon ID
	    		String weaponIDinput = weaponID.getText();
	    		
	    		//collect updated weapon info - added 11/8/2018(user feedback update)
	    		String[][] units = updateOther("weapon");
	    		
	    		//check if the user input exists in the system  - added 11/8/2018(user feedback update)
	    		boolean inArray = false;
	    		for (int i = 0 ; i < units.length ; i++)
	    		{
	    			if ( units[i][0].equals(weaponIDinput))
	    				inArray = true;				
	    		}
	    		
	    		//proceed only if the provided ID exists - added 11/8/2018(user feedback update)
	    		if ( inArray == false)
	    			weaponDeleteLbl.setText("Weapon #" + weaponIDinput + " does not exist. delete failed.");
	    		else
	    		{
	    			try (
    					//connect to database hosted locally
    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
    					Statement stmt = conn.createStatement();
    					)
	    			{
	    				//execute the delete statement
	    				stmt.execute("delete from weapon where weaponid = " + weaponIDinput);
	    				weaponDeleteLbl.setText("Weapon #" + weaponIDinput + " successfully deleted"); //added 11/8/2018(user feedback update)
	    			}
	    		
	    			//if weapon to be deleted is in use on a unit this error handling will inform use that the ability cannot be deleted
	    			catch (SQLIntegrityConstraintViolationException ex)
	    			{
	    				weaponDeleteLbl.setText("Weapons cannot be deleted while in use by a unit");
	    			}
	    		
	    			//remind user of acceptable ID's to use
	    			catch (SQLException ex)
	    			{
	    				weaponDeleteLbl.setText("You must enter an existing ID number in integer format");
	    			}
	    		}
	    	}
	        public void widgetDefaultSelected(SelectionEvent event)
	        {
	        }
	    });
	    
	    weaponAdd.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		/*the following variables are used to create the add/update sql string
	    		 * variables are initialized to default values which are replaced if there is user input
	    		 * weaponIDinput is checked for validity later and provides an error message to user
	    		 */
	    		String name = "no name";
	    		if (weaponName.getText() != "")
	    			name = weaponName.getText();
	    		
	    		String distance = "0";
	    		if (weaponDistance.getText() != "")
	    			distance = weaponDistance.getText();
	    		
	    		String type = "no type";
	    		if (weaponType.getText() != "")
	    			type = weaponType.getText();
	    		
	    		String strength = "0";
	    		if (weaponStrength.getText() != "")
	    			strength = weaponStrength.getText();
	    		
	    		String penetration = "0";
	    		if (weaponPenetration.getText() != "")
	    			penetration = weaponPenetration.getText();
	    		
	    		String damage = "0";
	    		if (weaponDamage.getText() != "")
	    			damage = weaponDamage.getText();
	    		
	    		String points = "0";
	    		if (weaponPoints.getText() != "")
	    			points = weaponPoints.getText();
	    		
	    		String weaponIDinput = weaponID.getText();
	    		String strAddUpdate = "";
	    		
	    		boolean goodIntID = false;
	    		boolean goodIntPoints = false;
	    		
	    		//tracks if this is an add or update action
	    		boolean weaponUpdate = false;
	    		
	    		/*this try statement checks if the weapon ID is a valid integer by attempting to parse it
	    		 * if successful the boolean goodInt allows the following IF statement to fire proceeding with the add/update
	    		 * if the try is unsuccessful an error message is presented to the user
	    		*/
	    		try 
	    		{
    				if (Integer.parseInt(weaponIDinput) >= 0)
    					goodIntID = true;
    				else
    	    			weaponIDcheck.setText("You must enter a valid Integer");
	    		}
	    		catch (NumberFormatException ex)
	    		{
	    			weaponIDcheck.setText("You must enter a valid Integer");
	    		}
	    		
	    		try 
	    		{
    				if (Integer.parseInt(points) >= 0)
    					goodIntPoints = true;
    				else
    	    			weaponPointsCheck.setText("You must enter a valid Integer");
	    		}
	    		catch (NumberFormatException ex)
	    		{
	    			weaponPointsCheck.setText("You must enter a valid Integer");
	    		}
	    		
	    		//check if weapon ID and Points are valid integers
	    		if (goodIntID && goodIntPoints)
	    		{
	    			//get updated weapon list from database
		    	    String[][] units = updateOther("weapon");
		    	    //iterate through all weapons, set update to true if statsIDinput(the user input stats ID) exists in the the database
		    	    for (int i=0 ; i < units.length ; i++)
		    	    {
		    	    	//these are safe parses as both values are tested in other areas
		    	    	if (Integer.parseInt(units[i][0]) == Integer.parseInt(weaponIDinput))
		    	    	{
		    	    		weaponUpdate = true;
		    	    		if (weaponName.getText() == "")
		    	    			name = units[i][8];
		    	    		if (weaponDistance.getText() == "")
		    	    			distance = units[i][9];
		    	    		if (weaponType.getText() == "")
		    	    			type = units[i][10];
		    	    		if (weaponStrength.getText() == "")
		    	    			strength = units[i][11];
		    	    		if (weaponPenetration.getText() == "")
		    	    			penetration = units[i][12];
		    	    		if (weaponDamage.getText() == "")
		    	    			damage = units[i][13];
		    	    		if (weaponPoints.getText() == "")
		    	    			points = units[i][17];
		    	    		
		    	    	}
		    	    }
		    	    
		    	    //creates correct add/update statement base on previous test
	    			if(weaponUpdate)
	    			{
	    				
	    				//create SQL update statement
	    				strAddUpdate = "update weapon set name = '" + name + "' , distance = " + distance + " , type = '" + type + "' , strength = " + strength + 
	    						" , penetration = " + penetration + " , damage = " + damage + " , points = " + points + " where weaponid = " + weaponIDinput;
	    			}
	    			else
	    			{
	    				//create SQL add statement
	    				strAddUpdate = "insert into weapon values(" + weaponIDinput + ", " + "'" + name + "'" + ", " + "" + distance + "" + ", '" + type + 
	    						"', " + "" + strength + "" + ", " + "" + penetration + "" + ", " + damage + "" + ", " + points + ")";
	    			}
	    			
	    			
	    			//this try connects to the database and executes the add/update statement
	    			try (
	    					//connect to database hosted locally
	    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
	    					Statement stmt = conn.createStatement();
	    					)
	    			{
	    				//execute the add/update statement
	    				stmt.execute(strAddUpdate);
	    				if(weaponUpdate) //checks for update or add to provide correct user feedback  - added 11/8/2018(user feedback update)
	    					weaponIDcheck.setText("Weapon #" + weaponIDinput + " sucessfully updated.");
	    				else
	    					weaponIDcheck.setText("ID #" + weaponIDinput + " sucessfully added as a new weapon.");
	    			}
	    			catch (SQLException ex)
	    			{
	    				System.out.println(ex);
	    			}
	    
	    		}
	    	}
	        	public void widgetDefaultSelected(SelectionEvent event)
	        	{
	        	}
	        });
   	    
		//**************************************End Update 10/25/2018 Weapons Tab added********************************************
	    
	  //**************************************Update 11/9/2018 Unit Tab added********************************************
	    TabItem model = new TabItem(tabFolder, SWT.NONE);
	    model.setText("Units");
	    
	    Composite unitComposite = new Composite(tabFolder, SWT.NONE);
	    model.setControl(unitComposite);
	    
	    Label lblNewLabel_14 = new Label(unitComposite, SWT.NONE);
	    lblNewLabel_14.setBounds(10, 10, 55, 15);
	    lblNewLabel_14.setText("Unit ID");
	    
	    Label lblNewLabel_15 = new Label(unitComposite, SWT.NONE);
	    lblNewLabel_15.setBounds(117, 10, 55, 15);
	    lblNewLabel_15.setText("Model");
	    
	    Label lblNewLabel_16 = new Label(unitComposite, SWT.NONE);
	    lblNewLabel_16.setBounds(253, 10, 55, 15);
	    lblNewLabel_16.setText("Psyker");
	    
	    Label lblNewLabel_17 = new Label(unitComposite, SWT.NONE);
	    lblNewLabel_17.setBounds(382, 10, 55, 15);
	    lblNewLabel_17.setText("Points");
	    
	    Label lblNewLabel_18 = new Label(unitComposite, SWT.NONE);
	    lblNewLabel_18.setBounds(10, 58, 76, 15);
	    lblNewLabel_18.setText("Stats ID");
	    
	    Label lblNewLabel_19 = new Label(unitComposite, SWT.NONE);
	    lblNewLabel_19.setBounds(117, 58, 76, 15);
	    lblNewLabel_19.setText("Weapon ID");
	    
	    Label lblNewLabel_20 = new Label(unitComposite, SWT.NONE);
	    lblNewLabel_20.setBounds(253, 58, 76, 15);
	    lblNewLabel_20.setText("Ability ID");
	    
	    unitID = new Text(unitComposite, SWT.BORDER);
	    unitID.setBounds(10, 31, 91, 21);
	    
	    unitModel = new Text(unitComposite, SWT.BORDER);
	    unitModel.setBounds(117, 31, 91, 21);
	    
	    Combo unitCaster = new Combo(unitComposite, SWT.NONE);
	    unitCaster.setItems(new String[] {"Yes", "No"});
	    unitCaster.setBounds(253, 31, 91, 23);
	    unitCaster.setText("No");
	    
	    unitPoints = new Text(unitComposite, SWT.BORDER);
	    unitPoints.setBounds(382, 31, 76, 21);
	    
	    Combo unitStats = new Combo(unitComposite, SWT.NONE);
	    unitStats.setBounds(10, 79, 91, 23);
	    
	    Combo unitWeapon = new Combo(unitComposite, SWT.NONE);
	    unitWeapon.setBounds(117, 79, 91, 23);
	    
	    Combo unitAbility = new Combo(unitComposite, SWT.NONE);
	    unitAbility.setBounds(253, 79, 91, 23);
	    
	    Label unitIDcheck = new Label(unitComposite, SWT.NONE);
	    unitIDcheck.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    unitIDcheck.setBounds(10, 108, 448, 15);
	    
	    Label unitPointsCheck = new Label(unitComposite, SWT.NONE);
	    unitPointsCheck.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    unitPointsCheck.setBounds(382, 58, 295, 15);
	    
	    Label unitDeleteLbl = new Label(unitComposite, SWT.NONE);
	    unitDeleteLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    unitDeleteLbl.setBounds(199, 152, 503, 15);
	    
	    Button unitAdd = new Button(unitComposite, SWT.NONE);
	    unitAdd.setBounds(10, 142, 75, 25);
	    unitAdd.setText("Add/Update");
	    //*****************************************begin unit add/update - added 11/15/2018**************************************
	    unitAdd.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		/*the following variables are used to create the add/update sql string
	    		 * variables are initialized to default values which are replaced if there is user input
	    		 * unitIDinput is checked for validity later and provides an error message to user
	    		 * stats/weapon/ability ID cannot be empty and provide an error msg to the user if they are blank
	    		 */
	    		String name = "no name";
	    		if (unitModel.getText() != "")
	    			name = unitModel.getText();
	    		
	    		String caster = "0";
	    		if (unitCaster.getText() == "Yes")
	    			caster = "1";
	    			    			    		
	    		String points = "0";
	    		if (unitPoints.getText() != "")
	    			points = unitPoints.getText();
	    		
	    		String statsID = "";
	    		String weaponID = "";
	    		String abilityID = "";
	    		
	    		boolean cont = true;
	    		int charPosition = 5;
	    			  
	    		//only attempt to find ID value from the string if the string is not empty (ie. only works if user provided an ID)
	    		if (unitStats.getText() != "")
	    		{
	    			statsID = "" + unitStats.getText().charAt(4); //first digit of ID is always at position 4
	    			cont = true; //reset loop condition
	    			charPosition = 5; //reset next position to 5
	    			
	    			do
	    			{
	    				if (unitStats.getText().charAt(charPosition) != ',') //ID is comma delineated, if no comma is found add next ID digit and continue loop
	    				{
	    					statsID = statsID + unitStats.getText().charAt(charPosition);
	    					charPosition++;
	    				}
	    				else
	    					cont = false;
	    			}
	    			while(cont);
	    		}
	    		
	    		//only attempt to find ID value from the string if the string is not empty (ie. only works if user provided an ID)
	    		if (unitWeapon.getText() != "")
	    		{
	    			weaponID = "" + unitWeapon.getText().charAt(4);
	    			cont = true; //reset loop condition
	    			charPosition = 5; //reset next position to 5
	    			do
	    			{
	    				if (unitWeapon.getText().charAt(charPosition) != ',') //ID is comma delineated, if no comma is found add next ID digit and continue loop
	    				{
	    					weaponID = weaponID + unitWeapon.getText().charAt(charPosition);
	    					charPosition++;
	    				}
	    				else
	    					cont = false;
	    			}
	    			while(cont);
	    		}
	    		
	    		//only attempt to find ID value from the string if the string is not empty (ie. only works if user provided an ID)
	    		if (unitAbility.getText() != "")
	    		{
	    			abilityID = "" + unitAbility.getText().charAt(4);
	    			cont = true; //reset loop condition
	    			charPosition = 5; //reset next position to 5
	    			do
	    			{
	    				if (unitAbility.getText().charAt(charPosition) != ',') //ID is comma delineated, if no comma is found add next ID digit and continue loop
	    				{
	    					abilityID = abilityID + unitAbility.getText().charAt(charPosition);
	    					charPosition++;
	    				}
	    				else
	    					cont = false;
	    			}
	    			while(cont);
	    		}
	    		
	    		
	    		String unitIDinput = unitID.getText();
	    		String strAddUpdate = "";
	    		
	    		boolean goodInput = false;
	    		boolean goodInputPoint = false;
	    		
	    		//tracks if this is an add or update action
	    		boolean unitUpdate = false;
	    		
	    		/*this try statement checks if the unit ID is a valid integer by attempting to parse it
	    		 * if successful the boolean goodInput remains true
	    		 * if the try is unsuccessful an error message is presented to the user
	    		*/
	    		try 
	    		{
    				if (Integer.parseInt(unitIDinput) >= 0 )
    					goodInput = true;
    				else
    	    			unitIDcheck.setText("You must enter a valid Integer for ID");
	    		}
	    		catch (NumberFormatException ex)
	    		{
	    			unitIDcheck.setText("You must enter a valid Integer for ID");
	    		}
	    		
	    		/*this try statement checks if the point cost is a valid integer by attempting to parse it
	    		 * if successful the boolean goodInput remains true
	    		 * if the try is unsuccessful an error message is presented to the user
	    		*/
	    		try 
	    		{
    				if (Integer.parseInt(points) >= 0)
    					goodInputPoint = true;
    				else
    	    			unitPointsCheck.setText("You must enter a valid Integer for points");
	    		}
	    		catch (NumberFormatException ex)
	    		{
	    			unitPointsCheck.setText("You must enter a valid Integer for points");
	    		}
	    		
	    		/*this try statement checks if the stat/weapon/ability ID's are populated
	    		 * if successful the boolean goodInput remains true
	    		 * if the try is unsuccessful an error message is presented to the user
	    		*/
	    		if (unitWeapon.getText() == "" || unitAbility.getText() == "" || unitStats.getText() == "")
	    		{
	    			goodInput = false;
	    			unitIDcheck.setText("stat/weapon/ability ID cannot be empty. Please click View All Units button.");
	    		}
	    		
	    		
	    		
	    		//check if unit ID and Points are valid integers
	    		if (goodInput && goodInputPoint)
	    		{
	    			
	    			//get updated weapon list from database
		    	    String[][] units = updateOther("unit");
		    	    //iterate through all units, set update to true if unitIDinput(the user input unit ID) exists in the the database
		    	    for (int i=0 ; i < units.length ; i++)
		    	    {
		    	    	//these are safe parses as both values are tested in other areas
		    	    	if (Integer.parseInt(units[i][0]) == Integer.parseInt(unitIDinput))
		    	    	{
		    	    		unitUpdate = true;
		    	    		if (unitModel.getText() == "")
		    	    			name = units[i][1];
		    	    		if (unitCaster.getText() == "")
		    	    			caster = units[i][19];
		    	    		if (unitPoints.getText() == "")
		    	    			points = units[i][17];	    	    
		    	    	}
		    	    }
		    	    
		    	    //creates correct add/update statement base on previous test
	    			if(unitUpdate)
	    			{
	    				
	    				//create SQL update statement
	    				strAddUpdate = "update unit set model = '" + name + "' , points = " + points + " , caster = " + caster + " , statsid = " + statsID + 
	    						" , weaponid = " + weaponID + " , abilityid = " + abilityID + " , date = CURRENT_TIMESTAMP where unitid = " + unitIDinput;
	    			}
	    			else
	    			{
	    				//create SQL add statement
	    				strAddUpdate = "insert into unit values(" + unitIDinput + ", " + "'" + name + "'" + ", " + "" + points + "" + ", " + caster + 
	    						", " + "" + statsID + "" + ", " + "" + weaponID + "" + ", " + abilityID + "" + ", CURRENT_TIMESTAMP )";
	    			}
	    			
	    			
	    			//this try connects to the database and executes the add/update statement
	    			try (
	    					//connect to database hosted locally
	    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
	    					Statement stmt = conn.createStatement();
	    					)
	    			{
	    				//execute the add/update statement
	    				stmt.execute(strAddUpdate);
	    				if(unitUpdate) //checks for update or add to provide correct user feedback  - added 11/8/2018(user feedback update)
	    					unitIDcheck.setText("Unit #" + unitIDinput + " sucessfully updated.");
	    				else
	    					unitIDcheck.setText("ID #" + unitIDinput + " sucessfully added as a new unit.");
	    			}
	    			catch (SQLException ex)
	    			{
	    				System.out.println(ex);
	    			}
	    
	    		}
	    	}
	        	public void widgetDefaultSelected(SelectionEvent event)
	        	{
	        	}
	        });
	    
	    
	    
	    
	    //*****************************************end unit add/update - added 11/15/2018**************************************
	    Button unitDelete = new Button(unitComposite, SWT.NONE);
	    unitDelete.setBounds(118, 142, 75, 25);
	    unitDelete.setText("Delete");
	    unitDelete.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) 
	    	{
	    		//store user entered unit ID
	    		String unitIDinput = unitID.getText();
	    		
	    		//collect updated unit info
	    		String[][] units = updateOther("unit");
	    		
	    		//check if the user input exists in the system
	    		boolean inArray = false;
	    		for (int i = 0 ; i < units.length ; i++)
	    		{
	    			if ( units[i][0].equals(unitIDinput))
	    				inArray = true;				
	    		}
	    		
	    		//proceed only if the provided ID exists
	    		if ( inArray == false)
	    			unitDeleteLbl.setText("Unit #" + unitIDinput + " does not exist. delete failed.");
	    		else
	    		{
	    			try (
    					//connect to database hosted locally
    					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
    					Statement stmt = conn.createStatement();
    					)
	    			{
	    				//execute the delete statement
	    				stmt.execute("delete from unit where unitid = " + unitIDinput);
	    				unitDeleteLbl.setText("Unit #" + unitIDinput + " successfully deleted");
	    			}
	    		
	    			//remind user of acceptable ID's to use
	    			catch (SQLException ex)
	    			{
	    				unitDeleteLbl.setText("You must enter an existing ID number in integer format");
	    			}
	    		}
	    	}
	        public void widgetDefaultSelected(SelectionEvent event)
	        {
	        }
	    });
	    
	    
	    
	    Button unitView = new Button(unitComposite, SWT.NONE);
	    unitView.setBounds(836, 142, 112, 25);
	    unitView.setText("View All Units");
	    unitView.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		

	    		//begin process to update combo boxes with accurate ID values
	    		//fill unit array for stats
	    		String[][] units = updateOther("stats");
	    		//initialize combo box array for number of stats
	    		String[] unitCombo = new String[units.length];
	    		//iterate through stats array and apply ID's and name to combo box array 
	    		for (int i=0 ; i < units.length ; i++)
	    		{
	    			unitCombo[i] = "ID: " + units[i][0] + ", " + units[i][1];
	    		}
	    		//assign values to stat id combo box
	    		unitStats.setItems(unitCombo);
	    		unitStats.setText(unitCombo[0]);
	    		
	    		//fill unit array for ability
	    		units = updateOther("ability");
	    		//reinitialize combo box array for number of abilities
	    		unitCombo = new String[units.length];
	    		//iterate through ability array and apply ID's and name to combo box array 
	    		for (int i=0 ; i < units.length ; i++)
	    		{
	    			unitCombo[i] = "ID: " + units[i][0] + ", " + units[i][16];
	    		}
	    		//assign values to ability id combo box
	    		unitAbility.setItems(unitCombo);
	    		unitAbility.setText(unitCombo[0]);
	    		
	    		//fill unit array for weapon
	    		units = updateOther("weapon");
	    		//reinitialize combo box array for number of weapons
	    		unitCombo = new String[units.length];
	    		//iterate through weapon array and apply ID's and name to combo box array 
	    		for (int i=0 ; i < units.length ; i++)
	    		{
	    			unitCombo[i] = "ID: " + units[i][0] + ", " + units[i][8];
	    		}
	    		//assign values to weapon combo box
	    		unitWeapon.setItems(unitCombo);
	    		unitWeapon.setText(unitCombo[0]);
	    		
	    		//clear error message
	    		unitIDcheck.setText("");
	    		unitDeleteLbl.setText("");
	    		unitPointsCheck.setText("");
	    	
	    		//obtain up to date unit info from database and store locally
	    		units = updateOther("unit");
	    		//clear output window
	    		table.removeAll();
	    		
	    		//iterate through array to display all units
	    		for (int i=0 ; i < units.length ; i++)
	    		{
	    			String caster = "yes";
	    			if ( units[i][19] == "0" )
	    				caster = "no";
	    			TableItem item = new TableItem(table, SWT.NONE);
	    			item.setText(new String[] {units[i][0],units[i][1],units[i][2],null,null,null,null,null,null,units[i][9],null,null,null,null,null,units[i][15],null,null,units[i][17],units[i][18],caster});
	    		}
	    	}
	        public void widgetDefaultSelected(SelectionEvent event)
	        {                    
	        }
	    });
	    
	    
	  //**************************************End Update 11/9/2018 Unit Tab added********************************************
	    
	    //fill view tab combo box
		for (int i = 0 ; i < units.length ; i++)
		     selectModel.add(units[i][1]);
		
		//check status of last database connection and fill "errorLogo" label as appropriate 
	    if (goodConnect)
	    	errorLogo.setImage(new Image(tabDisplay, "40klogo.png"));
	    else
	    	errorLogo.setText("Database not detected.  Please ensure database \n" + "is running and restart program.");
	    
	    /********************************updated 10/11/2018******************************
	     * this event was modified to utilize the update method
	     * display model button click event begins
	    */
	    displayModel.addSelectionListener(new SelectionListener( ) {
	    	public void widgetSelected(SelectionEvent event) {   
	    		
	    		String[][] units = updateAll();
	    		
	    		//index 0 is "all models". check for this index then display all models if true
	    		table.removeAll();
	    		int comboIndex = selectModel.getSelectionIndex();
	        	if (comboIndex == 0)
	        	{
	        		for (int i=0 ; i < units.length ; i++)
	        		{
		    			String caster = "yes";
		    			if ( units[i][21] == "0" )
		    				caster = "no";
	        			TableItem item = new TableItem(table, SWT.NONE);
	        			item.setText(new String[] {units[i][0], units[i][1], units[i][20], units[i][2], units[i][3], units[i][4], units[i][5], units[i][6], units[i][7], units[i][8], units[i][9], units[i][10], units[i][11], units[i][12], units[i][13], units[i][16], units[i][17], units[i][18], units[i][14] + "+" + units[i][15], units[i][19], caster});
	        		}
	       		}
	        	
	        	//for any other index check to ensure default text is NOT displayed
	        	else
	        		if (comboIndex > 0)
	        		{
	        			{
	        				
	        				int i = comboIndex-1;
	    	    			String caster = "yes";
	    	    			if ( units[i][21] == "0" )
	    	    				caster = "no";
	        				TableItem item = new TableItem(table, SWT.NONE);
	        				item.setText(new String[] {units[i][0], units[i][1], units[i][20], units[i][2], units[i][3], units[i][4], units[i][5], units[i][6], units[i][7], units[i][8], units[i][9], units[i][10], units[i][11], units[i][12], units[i][13], units[i][16], units[i][17], units[i][18], units[i][14] + "+" + units[i][15], units[i][19], caster});
	        			}
	        		}
	    		selectModel.removeAll();
	    	    selectModel.setText("Select Model to View");
	    	    selectModel.add("View all Models");
	    		for (int i = 0 ; i < units.length ; i++)
	    		     selectModel.add(units[i][1]);
	        }
	    	//display model button click event ends

	        public void widgetDefaultSelected(SelectionEvent event)
	        {                    
	        }
	  });
	    	    

	    tabShell.open();
	    while (!tabShell.isDisposed()) {
	      if (!tabDisplay.readAndDispatch())
	        tabDisplay.sleep();
	    }
	    tabDisplay.dispose();
	  }
	
	/*******************************added 10/11/2018 ****************************************
	 * All program output is handled by accessing data stored in an array
	 * this method builds/updates that array by retrieving data from the database
	 * the method returns the array for use in the main method.
	*/
	static String[][] updateAll()
	{
		//number of rows in the database
		int rowCount;
		rowCount = 0;
		String[][] units;
		
		try (
				//connect to database hosted locally
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
		        Statement stmt = conn.createStatement();
		     )
		{
			//execute select query on database to find all unitIDs(primary key for master table)
	        String strSelect = "select unitid from unit";	 
	        ResultSet rset = stmt.executeQuery(strSelect);
	        //iterate through table to find the number of rows and correctly set rowCount variable
	        while(rset.next()) { 
	           ++rowCount;
	        }

		}
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
		
		//create array to store unit table. rowCount derived from database. 20 columns is known based on database design.
        units = new String[rowCount][22];
        
        try (
        		//connect to database hosted locally
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
		        Statement stmt = conn.createStatement();
		     )
		{
        	//execute select query on database. Join all tables to return all information from all 4 tables
	        String strSelect = "select u.unitid, u.model, s.name, s.ws, s.bs, s.strength, s.toughness, s.wounds, s.save, w.name, w.distance, w.type, w.strength, w.penetration, w.damage, u.points, w.points, a.name, a.radius, a.description, u.date, u.caster \r\n" + 
	        		"from unit u, stats s, weapon w, ability a\r\n" + 
	        		"where u.statsid = s.statsid\r\n" + 
	        		"and u.weaponid = w.weaponid\r\n" + 
	        		"and u.abilityid = a.abilityid;";	 
	        ResultSet rset = stmt.executeQuery(strSelect);
	        // row count is already known. iterate through each row, storing all data in an array
	        for (int i=0 ; i < rowCount ; i++)
	        {
		     rset.next();
	       	 units[i][0] = rset.getString("u.unitid");
	       	 units[i][1] = rset.getString("u.model");
	       	 units[i][20] = rset.getString("s.name");
	       	 units[i][2] = rset.getString("s.ws");
	       	 units[i][3] = rset.getString("s.bs");
	       	 units[i][4] = rset.getString("s.strength");
	       	 units[i][5] = rset.getString("s.toughness");
	       	 units[i][6] = rset.getString("s.wounds");
	       	 units[i][7] = rset.getString("s.save");
	       	 units[i][8] = rset.getString("w.name");
	       	 units[i][9] = rset.getString("w.distance");
	       	 units[i][10] = rset.getString("w.type");
	       	 units[i][11] = rset.getString("w.strength");
	       	 units[i][12] = rset.getString("w.penetration");
	       	 units[i][13] = rset.getString("w.damage");
	       	 units[i][14] = rset.getString("u.points");
	       	 units[i][15] = rset.getString("w.points");
	       	 units[i][16] = rset.getString("a.name");
	       	 units[i][17] = rset.getString("a.radius");
	       	 units[i][18] = rset.getString("a.description");
	       	 units[i][19] = rset.getString("u.date");
	       	 units[i][21] = rset.getString("u.caster");
	        } 
	        goodConnect = true;
		}
		catch (SQLException ex)
		{
			goodConnect = false;
		}
        return units;
	}
	
	/***********************************Added 10/11/2018*******************************
	 * This method accepts a database table name as the argument
	 * return is a single array containing all the table from that table formatted for in program output
	 */
	
	static String[][] updateOther(String table)
	{
		//number of rows in the database
		int rowCount;
		rowCount = 0;
		String[][] tableArray;
		
		try (
				//connect to database hosted locally
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
		        Statement stmt = conn.createStatement();
		     )
		{
			//execute select query on database to find all unitIDs(primary key for master table)
	        String strSelect = ("select " + table + "id " + "from " + table);
	        ResultSet rset = stmt.executeQuery(strSelect);
	        //iterate through table to find the number of rows and correctly set rowCount variable
	        while(rset.next()) { 
	           ++rowCount;
	        }

		}
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
		
		//create array to store unit table. rowCount derived from database. 20 columns is known based on database design.
        tableArray = new String[rowCount][20];
        
        try (
        		//connect to database hosted locally
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warhammer?useSSL=false", "root", "");
		        Statement stmt = conn.createStatement();
		     )
		{
        	String strSelect;
        	switch (table)
        	{
        	case "ability":
        		strSelect = "select abilityid, name, description, radius \r\n" +
        	"from ability";
        		break;
        	case "weapon":
        		strSelect = "select weaponid, name, distance, type, strength, penetration, damage, points \r\n" +
        	"from weapon"; //updated 10/25/2018 added case for weapon
        		break;
        	case "stats":
        		strSelect = "select statsid, name, ws, bs, strength, toughness, wounds, save \r\n" +
        	"from stats"; //updated 10/24/2018 added case for stats
        		break;
        	case "unit":
        		strSelect = "select u.unitid, u.model, u.points, u.caster, u.date, s.name, w.name, a.name \r\n" +
        				"from unit u, stats s, weapon w, ability a\r\n" + 
    	        		"where u.statsid = s.statsid\r\n" + 
    	        		"and u.weaponid = w.weaponid\r\n" + 
    	        		"and u.abilityid = a.abilityid;";	  //updates 11/9/2018 added case for unit
        		break;
        	default :
        		strSelect = "";
        	
        	}
	        ResultSet rset = stmt.executeQuery(strSelect);
	        // row count is already known. iterate through each row, storing all data in an array
        	switch (table)
        	{
        	case "ability":
        		for (int i=0 ; i < rowCount ; i++)
    	        {
    		     rset.next();
    	       	 tableArray[i][0] = rset.getString("abilityid");
    	       	 tableArray[i][16] = rset.getString("name");
    	       	 tableArray[i][17] = rset.getString("description");
    	       	 tableArray[i][18] = rset.getString("radius");
    	        } 
        		break;
        		//updated 10/25/2018 added case for weapon
        	case "weapon":
        		for (int i=0 ; i < rowCount ; i++)
    	        {
    		     rset.next();
    	       	 tableArray[i][0] = rset.getString("weaponid");
    	       	 tableArray[i][8] = rset.getString("name");
    	       	 tableArray[i][9] = rset.getString("distance");
    	       	 tableArray[i][10] = rset.getString("type");
    	       	 tableArray[i][11] = rset.getString("strength");
    	       	 tableArray[i][12] = rset.getString("penetration");
    	       	 tableArray[i][13] = rset.getString("damage");
    	       	 tableArray[i][17] = rset.getString("points");
    	        } 
        		break;
        		//updated 10/24/2018 added case for stats
        	case "stats":
        		for (int i=0 ; i < rowCount ; i++)
    	        {
    		     rset.next();
    	       	 tableArray[i][0] = rset.getString("statsid");
    	       	 tableArray[i][1] = rset.getString("name");
    	       	 tableArray[i][2] = rset.getString("ws");
    	       	 tableArray[i][3] = rset.getString("bs");
    	       	 tableArray[i][4] = rset.getString("strength");
    	       	 tableArray[i][5] = rset.getString("toughness");
    	       	 tableArray[i][6] = rset.getString("wounds");
    	       	 tableArray[i][7] = rset.getString("save");
    	        } 
        		break;
        		//updated 11/9/2018 added case for unit
        	case "unit":
        		for (int i=0 ; i < rowCount ; i++)
    	        {
    		     rset.next();
    	       	 tableArray[i][0] = rset.getString("unitid");
    	       	 tableArray[i][1] = rset.getString("model");
    	       	 tableArray[i][2] = rset.getString("s.name");
    	       	 tableArray[i][9] = rset.getString("w.name");
    	       	 tableArray[i][15] = rset.getString("a.name");
    	       	 tableArray[i][17] = rset.getString("points");
    	       	 tableArray[i][19] = rset.getString("caster");
    	       	 tableArray[i][18] = rset.getString("date");
    	        } 
        		break;
        	
        	}
	        goodConnect = true;
		}
		catch (SQLException ex)
		{
			goodConnect = false;
		}
        return tableArray;
	}
	}

