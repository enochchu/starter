import webdriver from "selenium-webdriver";
import {Browser, Capabilities} from "selenium-webdriver/lib/capabilities.js";
import fetch from "node-fetch";

jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;

describe("DummyTest", () => {
	let driver;
	let baseURL = "https://www.google.com";

	it('true should equal to true', () => {
		expect(true).toBe(true);
	});

	describe('SeleniumTests', () => {
		let driver;

		beforeEach(() => {
			driver = new webdriver.Builder()
				.withCapabilities(Capabilities.firefox())
				.forBrowser(Browser.FIREFOX).build();
		});

		afterEach(() => {
			driver.quit();
		});

		it('should be able to navigate to a url and read the title', async () => {
			let pageTitle = "";
			await driver.get(baseURL);
			pageTitle = await driver.getTitle();
			expect(pageTitle).toContain('Google');
		});
	});

	it('should be able to read 200 when visiting Google', async () => {
		let status;
		await fetch(baseURL)
			.then(response => {status = response.status;})
			.catch(err => fail(err));
		console.log(status);
		expect(status).toBe(200);
	});
});
