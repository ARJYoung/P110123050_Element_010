Element 010 Read me Resubmission

This is a personal organiser application, designed to make a more mobile friendly way to assist the user by combining numerous APIs into one easy to use app. Hosting weather and reminders/schedules alongside notes.


This moodboard encapsulates how the application would look, hosting its own logo and a matching colour palette, with the requirements for the application, keeping in mind what should be the primary focus for building the application.



The fonts would be a different way of displaying the information, where Newsreader would be used for titles and headers, while Outfit is for standard text that would need to be read more by the user. The contrast is to avoid confusing the user on what information is being shown.


The logo is simplified as it would serve as the application icon alongside the logo the user would see as a header.

Wireframes:

These wireframes were made in mind using the Android Studio Navigation Drawers Views Activity, which gives the options to seamlessly change between different pages via the hamburger tool, this optimises usage with room to expand and make more pages should they be needed.





The landing page is designed to host all of the APIs in one go, albeit in a compact form, this would allow the user to quickly identify the information given, but broken down for easy reading, and if the user wanted to look into the information in more detail they can select the hamburger button in the top left corner to access the individual pages for a more detailed outlook on different topics, using the APIs.


The reminders page optimises Postman, an api designed to notify the user of any schedules and memos to read into at a later date. This would be user uploaded rather than automatically created when receiving an email or text message which has an “add to calendar” function.

As mentioned earlier, the landing page is only a snippet into what would be shown to the user as a quick glance, the weather page would optimise weather api, showing a few days in advance alongside other factors such as humidity, degrees in both celsius and fahrenheit. 

The notes page would work similarly to the reminders page, but without a date to notify the user but rather act as a little booklet to look back on such as meeting notes.

Api implementation:
Two apis were used 

Local.properties host the API keys together, keeping them privately and allowing them to be called through “weatherApiKey” and “echoApiKey” respectively. This protects the data that would be presented and avoids malicious tampering for the end user.


Within the files, there are a few classes for the APIs to use, such as location, which coincides with Weather API, seeing where the end user is as a means to give accurate data.


Main activity uses these previous classes as an actual way to present the data, for a class cannot communicate with the main program and has to be called within other files to be used optimally.

APIs used:
https://documenter.getpostman.com/view/9648928/SWEB3wHi#getting-started
https://www.weatherapi.com/docs



<img width="352" height="135" alt="Screenshot 2025-07-31 164051" src="https://github.com/user-attachments/assets/93982618-fa7f-4530-8485-5f352c094bb5" />
<img width="928" height="712" alt="Screenshot 2025-07-31 153859" src="https://github.com/user-attachments/assets/0f35b58f-4852-4de3-ba67-f9b4ec33ab81" />
<img width="689" height="111" alt="Screenshot 2025-07-31 151756" src="https://github.com/user-attachments/assets/3e7b3e5a-636f-4d60-9e84-9020bf651758" />
<img width="844" height="239" alt="Screenshot 2025-07-31 151749" src="https://github.com/user-attachments/assets/eb25c359-b335-411e-bb42-e27544f5cffd" />
<img width="347" height="335" alt="Screenshot 2025-07-31 151433" src="https://github.com/user-attachments/assets/5db4c8f6-fa4d-45d3-9589-c73a96d94a70" />
<img width="1625" height="432" alt="Screenshot 2025-07-31 145636" src="https://github.com/user-attachments/assets/8c4c28ad-0068-4d43-85b7-2fd6c080621e" />
<img width="1666" height="470" alt="Screenshot 2025-07-31 145614" src="https://github.com/user-attachments/assets/410b6050-a860-40af-b5f7-750b0f16c556" />
<img width="677" height="1012" alt="Screenshot 2025-07-31 140344" src="https://github.com/user-attachments/assets/a04a4d5a-7df3-481f-a130-e565a333b433" />
<img width="500" height="500" alt="personallymyorganiser" src="https://github.com/user-attachments/assets/82ed58a7-ecf2-4bcf-b46c-b187507753d3" />
![landingpage](https://github.com/user-attachments/assets/76fa1e02-7580-45db-af2a-5861a1198ebf)
![reminderspage](https://github.com/user-attachments/assets/e37937a5-b92a-4937-a162-17e7a93d3f54)
![weatherpage](https://github.com/user-attachments/assets/47c11dd1-49ab-4765-8dfd-8296036cfe13)
![notespage](https://github.com/user-attachments/assets/48170c18-d637-4ea7-8b14-4e2145999be8)
<img width="1920" height="1080" alt="mobileresubmoodboard" src="https://github.com/user-attachments/assets/622c679b-5111-4463-adff-4fa25baf8750" />
