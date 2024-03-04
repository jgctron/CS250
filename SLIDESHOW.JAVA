import javax.swing.*;
import java.awt.*;
import java.net.URL;
//SlideShow class that creates a JFrame to display a series of images with associated descriptions
public class SlideShow extends JFrame {
	// CardLayouts to manage the slideshow and text transitions
    private CardLayout cardLayoutSlides = new CardLayout();
    private CardLayout cardLayoutTexts = new CardLayout();
 // Panels to hold the slides and texts
    private JPanel slidePane = new JPanel(cardLayoutSlides);
    private JPanel textPane = new JPanel(cardLayoutTexts);
    private String[] imagePaths = {
        "/resources/kaylee-garret.jpg",
        "/resources/spa3.png",
        "/resources/stunning-poolside-luxury-hotel.jpg",
        "/resources/unnamed.jpg",
        "/resources/wellness-travel.jpg"
    };
 // Arrays to hold the paths to the images and the HTML-formatted descriptions
    private String[] descriptions = {
        "<html><center>" + "Detox and wellness travel focus: Showcasing images that represent wellness." + "<br>" + "“Nourish your body, mind, and soul.”" + "</center></html>",
        "<html><center>" + "Experience the tranquility and healing of a spa retreat." + "<br>" + "“Relaxation begins the moment you disconnect from the busy world.”" + "</center></html>",
        "<html><center>" + "Relax and rejuvenate with a scenic poolside view at a luxury wellness resort." + "<br>" + "“Serenity is not freedom from the storm, but peace amid the storm.”" + "</center></html>",
        "<html><center>" + "Find inner peace with wellness activities in serene environments." + "<br>" + "“Peace comes from within. Do not seek it without.”" + "</center></html>",
        "<html><center>" + "Embark on a journey of relaxation, detoxification, and stress-free travel." + "<br>" + "“Escape and breathe the air of new places.”" + "</center></html>"
    };
 // Constructor for SlideShow that sets up the JFrame
    public SlideShow() {
        setTitle("Detox/Wellness Travel SlideShow");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }
 // Method to initialize components in the JFrame
    private void initializeComponents() {
        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = createImageIcon(imagePaths[i], 800, 600); // Scale images to 800x600 pixels
            JLabel slideLabel = new JLabel(icon);
            slidePane.add(slideLabel, "Slide" + i);
         // Create labels for descriptions and add them to the textPane
            JLabel textLabel = new JLabel(descriptions[i], JLabel.CENTER);
            textLabel.setForeground(Color.WHITE); // Set text color to white for visibility
            textPane.add(textLabel, "Text" + i);
        }

        textPane.setBackground(Color.BLUE); // Set the background color of the text pane to blue
     // Action listener to show the previous slide and text
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
     // Panel for navigation buttons
        prevButton.addActionListener(e -> {
            cardLayoutSlides.previous(slidePane);
            cardLayoutTexts.previous(textPane);
        });
        nextButton.addActionListener(e -> {
            cardLayoutSlides.next(slidePane);
            cardLayoutTexts.next(textPane);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        add(slidePane, BorderLayout.CENTER);
        add(textPane, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
    }
 // Method to create an ImageIcon from a path with scaling to a specified width and height
    private ImageIcon createImageIcon(String path, int width, int height) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
        	// Scale the image to the specified width and height
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            System.err.println("File not found: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SlideShow frame = new SlideShow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
