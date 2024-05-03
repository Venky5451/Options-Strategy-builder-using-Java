package GUI;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.BorderLayout;
import GUI.Backend;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import GUI.Chart;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<String> positionListModel;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox_1;
    private JComboBox<String> comboBox_1_1_1;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnSell;
    static JSpinner spinner;
    private JSpinner spinner_1;
    private JLabel lblTotalMargin;
    private JLabel lblProfitLoss;
    private JLabel lblROI;
    private JLabel lblRiskReward;
    private JLabel lblDelta;
    private JLabel lblGamma;
    private JLabel lblTheta;
    private JLabel lblVega;
    private JSlider slider;
    static ArrayList<String> cp= new ArrayList<>();
    static ArrayList<Double> oprice= new ArrayList<>();
    static ArrayList<String> bs= new ArrayList<>();
    private JLabel valueLabel;

    /**
     * Launch the application.
     */
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Home() {
    	setBackground(SystemColor.textHighlight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1317, 661);
        setTitle("Opsrta-Staregy Builder");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Adding NavBar with navLinks
        JPanel navBar = new JPanel();
        navBar.setBounds(0, 0, 1725, 27);
        navBar.setBackground(SystemColor.textHighlight);
        contentPane.add(navBar);
                navBar.setLayout(null);
        
                // Adding Opstra label
                JLabel lblOpstra = new JLabel("Opstra");
                lblOpstra.setBounds(40, 5, 184, 14);
                navBar.add(lblOpstra);
                lblOpstra.setForeground(Color.WHITE);

        JLabel lblPortfolio = new JLabel("Portfolio");
        lblPortfolio.setForeground(Color.WHITE);
        lblPortfolio.setBounds(1093, 5, 74, 14);
        navBar.add(lblPortfolio);

        JLabel lblFutures = new JLabel("Futures");
        lblFutures.setBackground(Color.GREEN);
        lblFutures.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFutures.setForeground(Color.WHITE);
        lblFutures.setBounds(1142, 5, 82, 14);
        navBar.add(lblFutures);
        
                // Adding navigation links
                JLabel lblBuilder = new JLabel("Builder");
                lblBuilder.setHorizontalAlignment(SwingConstants.RIGHT);
                lblBuilder.setForeground(Color.WHITE);
                lblBuilder.setBounds(984, 5, 66, 14);
                navBar.add(lblBuilder);
                
                JComboBox comboBox = new JComboBox();
                comboBox.addActionListener((ActionListener) new ActionListener() {
                	public void actionPerformed1(ActionEvent e) {
                		
                	}

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
                });
                comboBox.setBackground(SystemColor.inactiveCaption);
                comboBox.setEditable(true);
                comboBox.setBounds(99, 69, 149, 34);
                comboBox.addItem("Nifty");
                comboBox.setSelectedItem("Nifty");
                contentPane.add(comboBox);
                
                JFormattedTextField frmtdtxtfldSelectIndex = new JFormattedTextField();
                frmtdtxtfldSelectIndex.setEditable(false);
                frmtdtxtfldSelectIndex.setText("Select Index/Stock");
                frmtdtxtfldSelectIndex.setBounds(99, 106, 149, 20);
                contentPane.add(frmtdtxtfldSelectIndex);
                
                positionListModel = new DefaultListModel<>();
                
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(109, 187, 508, 187);
                contentPane.add(scrollPane);
                JList<String> list_1 = new JList<>(positionListModel);
                scrollPane.setViewportView(list_1);
                
                comboBox_1 = new JComboBox<>();
              
                comboBox_1.setEditable(true);
                comboBox_1.setBackground(SystemColor.inactiveCaption);
                comboBox_1.setBounds(268, 69, 149, 34);
                comboBox_1.addItem("2024-05-02"); // Add initial date for testing
                
             // Calculate next three Thursdays
                LocalDate today = LocalDate.now();
                LocalDate nextThursday = today.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                LocalDate secondThursday = nextThursday.plusWeeks(1);
                LocalDate thirdThursday = nextThursday.plusWeeks(2);
                LocalDate fourthThursday = nextThursday.plusWeeks(3);
                LocalDate fifthThursday = nextThursday.plusWeeks(4);
                LocalDate sixthThursday = nextThursday.plusWeeks(4);
                LocalDate seventhThursday = nextThursday.plusWeeks(5);
                
             
                comboBox_1.addItem(secondThursday.toString());
                comboBox_1.addItem(thirdThursday.toString());
                comboBox_1.addItem(fourthThursday.toString());
                comboBox_1.addItem(fifthThursday.toString());
                comboBox_1.addItem(sixthThursday.toString());
                comboBox_1.addItem(seventhThursday.toString());


                // Set default selection to the first Thursday
                comboBox.setSelectedIndex(0);
                contentPane.add(comboBox_1);
                
                
                JFormattedTextField frmtdtxtfldSelect = new JFormattedTextField();
                frmtdtxtfldSelect.setEditable(false);
                frmtdtxtfldSelect.setText("Select Payoff date");
                frmtdtxtfldSelect.setBounds(268, 106, 149, 20);
                
                
                contentPane.add(frmtdtxtfldSelect);
                
                JFormattedTextField frmtdtxtfldStrikePrice = new JFormattedTextField();
                frmtdtxtfldStrikePrice.setEditable(false);
                frmtdtxtfldStrikePrice.setText("Strike price");
                frmtdtxtfldStrikePrice.setBounds(444, 106, 149, 20);
                contentPane.add(frmtdtxtfldStrikePrice);
                
                rdbtnNewRadioButton = new JRadioButton("Buy");
                rdbtnNewRadioButton.setBounds(713, 77, 53, 23);
                contentPane.add(rdbtnNewRadioButton);
                
                rdbtnSell = new JRadioButton("Sell");
                rdbtnSell.setBounds(713, 103, 53, 23);
                contentPane.add(rdbtnSell);
                
                comboBox_1_1_1 = new JComboBox<>();
                comboBox_1_1_1.setToolTipText("25-04-24");
                comboBox_1_1_1.setEditable(true);
                comboBox_1_1_1.setBackground(SystemColor.inactiveCaption);
                comboBox_1_1_1.setBounds(616, 69, 72, 34);
                comboBox_1_1_1.addItem("Call");
                comboBox_1_1_1.addItem("Put");
                contentPane.add(comboBox_1_1_1);
                
                contentPane.add(comboBox_1_1_1);
                
                JFormattedTextField frmtdtxtfldCallput = new JFormattedTextField();
                frmtdtxtfldCallput.setEditable(false);
                frmtdtxtfldCallput.setText("Call/Put");
                frmtdtxtfldCallput.setBounds(616, 106, 72, 20);
                contentPane.add(frmtdtxtfldCallput);
                
                JTextPane txtpnOptionStrategyBuilder = new JTextPane();
                txtpnOptionStrategyBuilder.setText("Option Strategy builder");
                txtpnOptionStrategyBuilder.setBounds(10, 38, 1426, 20);
                contentPane.add(txtpnOptionStrategyBuilder);
                
                JButton btnNewButton = new JButton("Add Position");
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		addPositionToList();
                	}
                });
                
                btnNewButton.setForeground(UIManager.getColor("Button.darkShadow"));
                btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
                btnNewButton.setBounds(972, 80, 165, 34);
                contentPane.add(btnNewButton);
                
                Chart creator = new Chart();
                JPanel panel = creator.createChartPanel(cp,bs,oprice);
                contentPane.add(panel);
                
                slider = new JSlider();
                slider.setMinimum(20500);
                slider.setMaximum(24500);
                slider.setMajorTickSpacing(50);
                slider.setSnapToTicks(true);
                slider.setBounds(733, 548, 546, 26);

                // Create label to display current value
                JLabel valueLabel = new JLabel();
                valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
                valueLabel.setBounds(733, 527, 546, 20); // Position it above the slider

                // Add a ChangeListener to update the value label
                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider source = (JSlider) e.getSource();
                        int value = source.getValue();
                        valueLabel.setText("Current Value: " + value);
                    }
                });

                // Initialize label with initial value
                valueLabel.setText("Current Value: " + slider.getValue());

                // Add components to the content pane
                contentPane.add(slider);
                contentPane.add(valueLabel);

                
                JSlider slider_1 = new JSlider();
                slider_1.setBounds(733, 585, 546, 26);
                contentPane.add(slider_1);
                
                JFormattedTextField frmtdtxtfldSpot = new JFormattedTextField();
                frmtdtxtfldSpot.setText("Spot (%)");
                frmtdtxtfldSpot.setEditable(false);
                frmtdtxtfldSpot.setBounds(660, 554, 53, 20);
                contentPane.add(frmtdtxtfldSpot);
                
                JFormattedTextField frmtdtxtfldExpiry = new JFormattedTextField();
                frmtdtxtfldExpiry.setText("Expiry");
                frmtdtxtfldExpiry.setEditable(false);
                frmtdtxtfldExpiry.setBounds(660, 585, 53, 26);
                contentPane.add(frmtdtxtfldExpiry);
                
                JFormattedTextField frmtdtxtfldStrategyPositions = new JFormattedTextField();
                frmtdtxtfldStrategyPositions.setText("Strategy Positions");
                frmtdtxtfldStrategyPositions.setEditable(false);
                frmtdtxtfldStrategyPositions.setBounds(99, 137, 149, 29);
                contentPane.add(frmtdtxtfldStrategyPositions);
                
                JButton btnReset = new JButton("Reset");
                btnReset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        positionListModel.clear();
                        cp.clear();
                        bs.clear();
                        oprice.clear();
                    }
                });
                btnReset.setForeground(UIManager.getColor("Button.darkShadow"));
                btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
                btnReset.setBounds(507, 137, 109, 29);
                contentPane.add(btnReset);
                
           
                spinner = new JSpinner();
                spinner.setBounds(444, 69, 149, 27);
                spinner.setModel(new SpinnerListModel(Backend.generatePriceList(20500, 24500, 50))); // Set model with prices
                contentPane.add(spinner);
                
                JFormattedTextField frmtdtxtfldPayoffChart = new JFormattedTextField();
                frmtdtxtfldPayoffChart.setText("Payoff Chart");
                frmtdtxtfldPayoffChart.setEditable(false);
                frmtdtxtfldPayoffChart.setBounds(642, 137, 149, 20);
                contentPane.add(frmtdtxtfldPayoffChart);
                
                JButton btnGetChart = new JButton("Get Chart");
                btnGetChart.setForeground(UIManager.getColor("Button.darkShadow"));
                btnGetChart.setBackground(UIManager.getColor("Button.darkShadow"));
                btnGetChart.setBounds(972, 133, 165, 34);
                contentPane.add(btnGetChart);
                
                btnGetChart.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Call method to generate chart panel
                        Chart creator = new Chart();
                        JPanel chartPanel = creator.createChartPanel(cp,bs,oprice);
                        panel.removeAll();
                        panel.add(chartPanel);
                        panel.revalidate();
                        panel.repaint();
                    }
                });
                
                JSeparator separator = new JSeparator();
                separator.setBounds(111, 388, 503, 11);
                contentPane.add(separator);
                
                JButton btnCalculateStrategyOutcomes = new JButton("Calculate Strategy Outcomes ");
                btnCalculateStrategyOutcomes.setForeground(UIManager.getColor("Button.darkShadow"));
                btnCalculateStrategyOutcomes.setBackground(UIManager.getColor("Button.darkShadow"));
                btnCalculateStrategyOutcomes.setBounds(107, 402, 507, 29);
                contentPane.add(btnCalculateStrategyOutcomes);
               
                btnCalculateStrategyOutcomes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Call method to calculate strategy outcomes
                    	updateCalculatedValues(cp,bs,oprice);
                    }
                });
               

                // Initialize JLabels in the constructor
                lblTotalMargin = new JLabel("Total Margin Requirement: ");
                lblTotalMargin.setBounds(111, 442, 198, 20);
                contentPane.add(lblTotalMargin);

                lblProfitLoss = new JLabel("Profit/Loss(On Expiry)     : ");
                lblProfitLoss.setBounds(109, 473, 200, 20);
                contentPane.add(lblProfitLoss);

                lblROI = new JLabel("RoI (Return on Investment): ");
                lblROI.setBounds(111, 504, 200, 20);
                contentPane.add(lblROI);

                lblRiskReward = new JLabel("Risk-Reward Ratio: ");
                lblRiskReward.setBounds(109, 541, 200, 20);
                contentPane.add(lblRiskReward);
                
             // Create JLabel for Gamma
                lblGamma = new JLabel("Gamma: ");
                lblGamma.setBounds(393, 442, 200, 20);
                contentPane.add(lblGamma);

                // Create JLabel for Delta
                lblDelta = new JLabel("Delta: ");
                lblDelta.setBounds(393, 473, 200, 20);
                contentPane.add(lblDelta);

                lblTheta = new JLabel("Theta");
                lblTheta.setBounds(393, 504, 200, 20);
                contentPane.add(lblTheta);
                
                lblVega = new JLabel("Vega");
                lblVega.setBounds(393, 541, 200, 20);
                contentPane.add(lblVega);

                spinner_1 = new JSpinner();
                spinner_1.setBounds(795, 73, 149, 27);
                contentPane.add(spinner_1);
                
                JFormattedTextField frmtdtxtfldCurrentSpotPrice = new JFormattedTextField();
                frmtdtxtfldCurrentSpotPrice.setText("Current Spot Price");
                frmtdtxtfldCurrentSpotPrice.setEditable(false);
                frmtdtxtfldCurrentSpotPrice.setBounds(795, 106, 149, 20);
                contentPane.add(frmtdtxtfldCurrentSpotPrice);
                
                
                
               
    }
    private void addPositionToList() {
        String strikePriceStr = spinner.getValue().toString();
        String expiry = comboBox_1.getSelectedItem().toString();
        String callPut = comboBox_1_1_1.getSelectedItem().toString();
        String buySell = rdbtnNewRadioButton.isSelected() ? "Buy" : "Sell";	
        String spotPriceStr = spinner_1.getValue().toString();
        
        // Parse strike price and spot price as doubles
        double strikePrice = Double.parseDouble(strikePriceStr);
        double spotPrice = Double.parseDouble(spotPriceStr);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate targetDate = LocalDate.parse(expiry, formatter);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the difference in days between the two dates
        double expiryDays = ChronoUnit.DAYS.between(currentDate, targetDate);
        double years = expiryDays / 365.25;
        
        // Calculate option price using Black-Scholes Model
        double optionPrice = BlackScholesModel.calculateOptionPrice(spotPrice, strikePrice, 0.16, 0.02, 0.10, callPut);

        // Add the position to the list model
        String position = strikePriceStr + "          " + expiry + "             " + callPut + "           " + buySell + "          " + df.format(optionPrice);
        positionListModel.addElement(position);
        cp.add(callPut);
        bs.add(buySell);
        oprice.add(optionPrice);
    }
    
    private void updateCalculatedValues(ArrayList<String> cp,ArrayList<String> bs,ArrayList<Double> oprice2) {
        // Calculate Total Margin Requirement, Profit/Loss, RoI, and Risk-Reward ratio
        double totalMargin = Backend.calculateTotalMargin(cp,bs,oprice2);
        String callPut = comboBox_1_1_1.getSelectedItem().toString();
        double spotPriceStr = slider.getValue();
        String strikePriceStr = spinner.getValue().toString();
        double strikePrice = Double.parseDouble(strikePriceStr);
        double spotPrice = spotPriceStr;
        
        double profitLoss = Backend.calculateProfitLoss(strikePrice,spotPrice,cp,bs,oprice2);
        double roi = Backend.calculateROI(strikePrice,spotPrice,cp,bs,oprice2);
        double riskReward = Backend.calculateRiskReward(cp,bs,oprice2);
        double Delta=BlackScholesModel.calculateDelta(spotPrice,strikePrice, 0.02, 0.1, 0.16,callPut);
        double Theta=BlackScholesModel.calculateTheta(spotPrice,strikePrice, 0.02, 0.1, 0.16,callPut);
        double Gamma=BlackScholesModel.calculateGamma(spotPrice,strikePrice, 0.02, 0.1,callPut);
        double Vega=BlackScholesModel.calculateVega(spotPrice,strikePrice, 0.02, 0.1,callPut);
        // Update JLabels with the calculated values
        lblTotalMargin.setText("Total Margin Requirement: " + df.format(totalMargin));
        lblProfitLoss.setText("Profit/Loss: " + df.format(profitLoss));
        lblROI.setText("RoI (Return on Investment): " + df.format(roi)+"%");
        lblRiskReward.setText("Risk-Reward Ratio: " + df.format(riskReward));
        lblDelta.setText("Delta :"+df.format(Delta));
        lblTheta.setText("Theta  :"+df.format(Theta));
        lblGamma.setText("Gamma  :"+df.format(Gamma));
        lblVega.setText("Vega:"+df.format(Vega));
  
    }
}

