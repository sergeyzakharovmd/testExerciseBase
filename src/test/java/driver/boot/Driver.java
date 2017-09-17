package driver.boot;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.eclipse.jetty.client.AuthenticationProtocolHandler.LOG;


public class Driver implements WebDriver, Runnable {

    protected ThreadLocal<RemoteWebDriver> threadDriver = null;
    public WebDriver webDriver;
    public String browser;

    @Before
    public void startBrowser() throws MalformedURLException, InterruptedException {

        browser = "chrome";

        if (System.getProperty("browser") == null && System.getProperty("environment") == null) {
            System.setProperty("browser", browser);
        } else if (System.getProperty("browser") == null) {
            System.setProperty("browser", browser);
        }

        if (System.getProperty("browser").equals("chrome"))
            startChrome();
        else if (System.getProperty("browser").equals("remote-chrome"))
            startRemoteChrome();
        else
            System.out.println("Browser not recognised: " + System.getProperty("browser") + ". Valid inputs are: chrome, ie, firefox or htmlunit.");
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
        } finally {
            quitBrowser();
        }
    }

    private synchronized void startRemoteChrome() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(Arrays.asList("allow-running-insecure-content", "ignore-certificate-errors"));
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();

            webDriver = new RemoteWebDriver(new URL("http://chromedriver:4444/wd/hub"), capabilities);
            LOG.info("Creating a new session of remote Chrome--------");
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            LOG.info("Cleared browser's cookies--------");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Hello from a thread!");
    }

    private synchronized void startChrome() {
        try {
            final String CHROME_BROWSER_PATH = "C:\\Users\\User\\IdeaProjects\\TestExerciseBase\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", CHROME_BROWSER_PATH);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors");

            webDriver = new ChromeDriver(options);
            LOG.info("Launching chrome Browser's new instance ------");
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            LOG.info("Cleared browser's cookies--------");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Quits webDriver and every window associated with it
     */
    public synchronized void quitBrowser() {
        close();
        quit();
        LOG.info("Driver session is going to Quit");
    }


    @Override
    public void get(String s) {
        webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }


}