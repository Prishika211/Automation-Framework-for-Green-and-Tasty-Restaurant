package com.epam.greenandtasty.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LocationDetailsPage extends BasePage {
    @FindBy(xpath = "//p[.='4.7']")
    private WebElement restaurantRating;

    @FindBy(css = "img[alt='Restaurant']")
    private WebElement restaurantPhoto;

    @FindBy(xpath = "//p[@class='mt-4 text-gray-700']")
    private WebElement restaurantDescription;

    @FindBy(xpath = "//h2[.='Specialty Dishes']")
    private WebElement specialityDishesSection;

    @FindBy(xpath = "//h4[@class='text-sm font-semibold  ']")
    private List<WebElement> dishNames;

    @FindBy(xpath = "//img[@class=' h-32 w-36 object-cover rounded-full mx-auto']")
    private List<WebElement> dishImages;

    @FindBy(xpath = ".//div[@class='flex justify-between text-sm text-gray-600 mt-2']/child::span[2]")
    private List<WebElement> dishWeights;

    @FindBy(xpath = ".//div[@class='flex justify-between text-sm text-gray-600 " + "mt-2']/child::span[1]")
    private List<WebElement> dishPrices;

    @FindBy(xpath = "//button[.='Service']")
    private WebElement serviceFilterTab;

    @FindBy(xpath = "//button[.='Cuisine Experience']")
    private WebElement cuisineFilterTab;

    @FindBy(xpath = "//div[@class='bg-white shadow rounded-lg p-4']/parent::div")
    private List<WebElement> reviews;

    @FindBy(xpath = "//select[@class='border border-green-600 p-2 rounded']")
    private WebElement sortingDropdown;

    @FindBy(xpath = "//option[.='Top rated first']")
    private WebElement topRatedOption;

    @FindBy(xpath = "//option[.='Low rated first']")
    private WebElement lowRatedOption;


    @FindBy(xpath = "//option[.='Newest first']")
    private WebElement newestOption;

    @FindBy(xpath = "//option[.='Oldest first']")
    private WebElement oldestOption;

    @FindBy(xpath = "//button[.='Next']")
    private WebElement paginationNext;

    @FindBy(xpath = "//button[.='Prev']")
    private WebElement paginationPrevious;

    public LocationDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void isRestaurantDetailsDisplayed() {
        waitForElement(restaurantRating);
        waitForElement(restaurantPhoto);
        waitForElement(restaurantDescription);
    }

    public boolean isDetailDisplayed(String detail) {
        return switch (detail) {
            case "Rating" -> {
                waitForElement(restaurantRating);
                yield restaurantRating.isDisplayed();
            }
            case "Photo" -> restaurantPhoto.isDisplayed();
            case "Description" -> restaurantDescription.isDisplayed();
            default -> false;
        };
    }

    public boolean isSpecialityDishesSectionDisplayed() {
        waitForElement(specialityDishesSection);
        return specialityDishesSection.isDisplayed();
    }

    public boolean isDishDetailValid() {
        for (WebElement dishName : dishNames) {
            if (dishName.getText().isEmpty()) return false;
        }

        for (WebElement dishImage : dishImages) {
            if (!dishImage.isDisplayed()) return false;
        }

        for (WebElement dishWeight : dishWeights) {
            if (dishWeight.getText().isEmpty()) return false;
        }

        for (WebElement dishPrice : dishPrices) {
            if (dishPrice.getText().isEmpty()) return false;
        }
        return true;
    }

    public void filterReviews(String filterType) {
        switch (filterType.toLowerCase()) {
            case "service" -> {
                waitForElement(serviceFilterTab);
                click(serviceFilterTab);
            }
            case "cuisine experience" -> {
                waitForElement(cuisineFilterTab);
                click(cuisineFilterTab);
            }
            default ->
                    throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }
    }

    public boolean isReviewListUpdated() {
        try {
            waitForElementBy(By.xpath("//div[@class='bg-white shadow rounded-lg p-4']"));
            List<WebElement> firstReview = webDriver.findElements(By.xpath("//div[@class='bg-white shadow rounded-lg p-4']"));
            return !firstReview.isEmpty();
        } catch (Exception e) {
            System.err.println("Failed to validate review list update: " + e.getMessage());
            return false;
        }
    }

    public void sortReviews(String sortOption) {
        waitForElement(sortingDropdown);
        click(sortingDropdown);
        switch (sortOption.toLowerCase()) {
            case "top rated first" -> click(topRatedOption);
            case "low rated first" -> click(lowRatedOption);
            case "oldest first" -> click(oldestOption);
            case "newest first" -> click(newestOption);
            default ->
                    throw new IllegalArgumentException("Invalid sort option: " + sortOption);
        }
    }

    public boolean isReviewListSorted(String sortingOption) {
        try {
            // Locate the SVG elements that represent the stars
            By svgAll = By.xpath("//div[@class='flex items-center gap-3']/div[3]/*[name()='svg']");
            List<WebElement> elements = webDriver.findElements(svgAll);

            // Calculate the number of reviews
            int[] numberOfStars = new int[elements.size() / 5];
            int j = 0;

            // Loop through the WebElements and calculate the number of stars for each review
            for (WebElement element : elements) {
                boolean isGoldStar = element.getAttribute("fill").equals("gold");
                if (isGoldStar) {
                    numberOfStars[j / 5]++;  // Increment the star count for the current review
                }
                j++;
            }

            // Switch statement to handle different sorting options
            return switch (sortingOption) {
                case "Top rated first" -> {
                    // Check if the list is in non-ascending order (higher ratings first)
                    for (int i = 0; i < numberOfStars.length - 1; i++) {
                        if (numberOfStars[i] < numberOfStars[i + 1]) {
                            yield false;
                        }
                    }
                    yield true;
                }
                case "Low rated first" -> {
                    // Check if the list is in non-descending order (lower ratings first)
                    for (int i = 0; i < numberOfStars.length - 1; i++) {
                        if (numberOfStars[i] > numberOfStars[i + 1]) {
                            yield false;
                        }
                    }
                    yield true;
                }
                case "Oldest first" -> {
                    for (int i = 0; i < elements.size() - 1; i++) {
                        String current = elements.get(i).getText().trim();
                        String next = elements.get(i + 1).getText().trim();
                        Date currentDate = convertToDate(current);
                        Date nextDate = convertToDate(next);
                        if (currentDate.after(nextDate)) { // Current date must be before or equal to the next date
                            yield false;
                        }
                    }
                    yield true;
                }
                case "Newest first" -> {
                    for (int i = 0; i < elements.size() - 1; i++) {
                        String current = elements.get(i).getText().trim();
                        String next = elements.get(i + 1).getText().trim();
                        Date currentDate = convertToDate(current);
                        Date nextDate = convertToDate(next);
                        if (currentDate.before(nextDate)) { // Current date must
                            // be before or equal to the next date
                            yield false;
                        }
                    }
                    yield true;
                }
                default -> {
                    throw new IllegalStateException("Unexpected value: " + sortingOption);
                }
            };
        } catch (Exception e) {
            System.err.println("Failed to validate review list sorting: " + e.getMessage());
            return false;
        }

    }

    private Date convertToDate(String dateString) {
        try {
            // Custom date-time format: DD:MM:YYYY, HH:MM
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy, HH:mm");

            // Convert the date format
            String[] parts = dateString.split(", "); // Split into [date, time]
            String datePart = parts[0]; // "22 Apr 2025"
            String timePart = parts[1].toLowerCase(); // "05:59 pm"

            // Replace month name with numeric value
            String[] dateParts = datePart.split(" "); // ["22", "Apr", "2025"]
            String day = dateParts[0];
            String month = getMonthAsNumber(dateParts[1]); // Convert month name to number
            String year = dateParts[2];

            // Convert PM to 24-hour time if needed
            String[] timeParts = timePart.split(":"); // ["05", "59 pm"]
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1].replaceAll("[^0-9]", "")); // Strip " pm"
            if (timePart.contains("pm") && hour != 12) {
                hour += 12; // Add 12 to convert PM to 24-hour format
            } else if (timePart.contains("am") && hour == 12) {
                hour = 0; // Midnight case
            }

            // Construct the standardized date-time string
            String normalizedDateTime = String.format("%s:%s:%s, %02d:%02d", day, month, year, hour, minute);

            // Return the parsed Date object
            return dateFormat.parse(normalizedDateTime);
        } catch (Exception e) {
            System.err.println("Failed to convert date: " + dateString + " - Error: " + e.getMessage());
            return null; // Return null if parsing fails
        }
    }

    // Helper to convert month name to numeric (e.g., Apr -> 04)
    private String getMonthAsNumber(String monthName) {
        switch (monthName.toLowerCase()) {
            case "jan":
                return "01";
            case "feb":
                return "02";
            case "mar":
                return "03";
            case "apr":
                return "04";
            case "may":
                return "05";
            case "jun":
                return "06";
            case "jul":
                return "07";
            case "aug":
                return "08";
            case "sep":
                return "09";
            case "oct":
                return "10";
            case "nov":
                return "11";
            case "dec":
                return "12";
            default:
                throw new IllegalArgumentException("Invalid month name: " + monthName);
        }
    }


    private boolean isDateOrdered(String current, String next) {
        try {
            // Example: Convert dates into LocalDate format and compare
            Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(current);
            Date nextDate = new SimpleDateFormat("yyyy-MM-dd").parse(next);
            return currentDate.after(nextDate) || currentDate.equals(nextDate);
        } catch (ParseException e) {
            System.err.println("Failed to parse review dates: " + e.getMessage());
            return false;
        }
    }

    public void goToNextPage() {
        waitForElement(paginationNext);
        click(paginationNext);
    }

    public void goToPreviousPage() {
        waitForElement(paginationPrevious);
        click(paginationPrevious);
    }

    public boolean isNextPaginationDisplayed() {
        return paginationNext.isDisplayed();
    }

    public boolean isPreviousPaginationDisplayed() {
        return paginationPrevious.isDisplayed();
    }

   public void scrollToReviewsSection(String sectionName){
            if("Service".equals(sectionName)){
            scrollToElement(serviceFilterTab);
        }
    }
}
