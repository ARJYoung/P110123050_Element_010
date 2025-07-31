Element 010 Read me Resubmission

This is a personal organiser application, designed to make a more mobile friendly way to assist the user with their daily lives and tasks. This was achieved by combining numerous APIs into one easy to use app, such as hosting weather and reminders/schedules alongside notes.

<img width="1920" height="1080" alt="mobileresubmoodboard" src="https://github.com/user-attachments/assets/472f41a6-ebb3-4a7d-811f-81a219dd4ea5" />

This moodboard encapsulates how the application would look, hosting its own logo and a matching colour palette, with the requirements for the application, keeping in mind what should be the primary focus for building the application.

<img width="352" height="135" alt="fonts" src="https://github.com/user-attachments/assets/d9dee970-6d19-4aca-b05e-10f0db2aaabb" />

The fonts would be a different way of displaying the information, where Newsreader would be used for titles and headers, while Outfit is for standard text that would need to be read more by the user. The contrast is to avoid confusing the user on what information is being shown where.

<img width="500" height="500" alt="personallymyorganiser" src="https://github.com/user-attachments/assets/25c6b766-509f-4f73-86ec-e0502aa0e171" />

The logo is simplified as it would serve as the application icon alongside the logo the user would see as a header. A simple logo works best for the icon as this allows the user to easily recognise the differences between this app and others.

Wireframes:

These wireframes were made in mind using the Android Studio Navigation Drawers Views Activity, which gives the options to seamlessly change between different pages via the hamburger tool, this optimises usage with room to expand and make more pages should they be needed.

![landingpage](https://github.com/user-attachments/assets/3b8d0b2d-04e0-4e93-a037-a4371af4dc20)

The landing page is designed to host all of the APIs in one go, albeit in a compact form, this would allow the user to quickly identify the information given, but broken down for easy reading, and if the user wanted to look into the information in more detail they can select the hamburger button in the top left corner to access the individual pages for a more detailed outlook on different topics, using the APIs.

![reminderspage](https://github.com/user-attachments/assets/5aa11273-7c21-40fb-9137-46f36ec7539d)

The reminders page optimises Postman, an api designed to notify the user of any schedules and memos to read into at a later date. This would be manually uploaded rather than automatically created when receiving an email or text message which has an “add to calendar” function.

![weatherpage](https://github.com/user-attachments/assets/252c7b92-6959-42c1-b10e-681212cc332c)

As mentioned earlier, the landing page is only a snippet into what would be shown to the user as a quick glance, the weather page would optimise weather api, showing a few days in advance alongside other factors such as humidity, degrees in both celsius and fahrenheit. 

![notespage](https://github.com/user-attachments/assets/d4d17467-0753-4fb1-a565-c741d639e3ed)

The notes page would work similarly to the reminders page, without a date to notify the user but rather act as a little booklet to look back on such as meeting notes.

Code fragments
Two apis were used 

<img width="844" height="239" alt="localproperties" src="https://github.com/user-attachments/assets/8ed1dd59-646f-46d5-8628-2c5c1e8f85ba" />

Local.properties host the API keys together, keeping them privately and allowing them to be called through “weatherApiKey” and “echoApiKey” respectively. This protects the data that would be presented and avoids malicious tampering for the end user.

<img width="347" height="335" alt="api and data" src="https://github.com/user-attachments/assets/211fa4ab-fcac-4d20-9915-e9519a6d22b1" />

Within the files, there are a few classes for the APIs to use, such as location, which coincides with Weather API, as having the location file set up allows the Weather API to determine where the end user currently is and give accurate weather data for that location.

<img width="928" height="712" alt="mainviewmodel" src="https://github.com/user-attachments/assets/571ffa8b-0a0b-49c4-bcbb-535b5ed63bc0" />

MainViewModel uses these previous classes as a way to visually present the data, for a class cannot communicate with the main program and has to be called within other files to be used optimally.

<img width="733" height="622" alt="mainactivity" src="https://github.com/user-attachments/assets/7383fe7c-d304-4a6d-a679-1bcf73cbe42d" />

This code snippet from MainActivity displays an array of calling actions for the MainViewModel, the communication between this file and the other will ensure that the application is able to show the content as required.

<img width="1261" height="382" alt="buildgradlekts" src="https://github.com/user-attachments/assets/3f219efc-0bba-4b2e-8e5b-c019c07c8b3d" />

Dependencies has this fragment before the implementations as a means to avoiding an error that claims duplicate classes within the build.gradle.kts:app.

<img width="1153" height="566" alt="weatherapiservice" src="https://github.com/user-attachments/assets/8e40de17-d171-4235-9e4a-818f96258c56" />

WeatherApiService builds on communicating with MainActivity as a means to observe the data that is shown to the user.

<img width="1154" height="741" alt="echoapiservice" src="https://github.com/user-attachments/assets/84eae1f8-100a-4d9b-bbf2-c515e89a9c5c" />

EchoApiService works in a similar way to WeatherApiService in which it calls upon the reminders api services.

<img width="525" height="558" alt="weatherresponse" src="https://github.com/user-attachments/assets/bc9aed38-afd4-4ce1-9bfe-1dd87c93a0ae" />

WeatherResponse

<img width="603" height="420" alt="location" src="https://github.com/user-attachments/assets/e52148ab-39df-4c3b-bcf6-1ceb08909dee" />

Location


Current

<img width="593" height="875" alt="current1" src="https://github.com/user-attachments/assets/ff20f754-f468-4bbd-8523-12b093e2f544" />
<img width="400" height="232" alt="current2" src="https://github.com/user-attachments/assets/720ce8b1-3e65-48be-b641-69f12745844d" />

Current

<img width="571" height="253" alt="condition" src="https://github.com/user-attachments/assets/ca78f948-82e0-4f3e-bb79-0df508eebb9c" />

Condition

<img width="525" height="558" alt="weatherresponse" src="https://github.com/user-attachments/assets/661e8d39-54ab-4409-9807-ec7b6488d94d" />

WeatherResponse encapsulates the data classes Current, Condition and Location, created in WeatherApi. This is proven to be more efficient when the data classes are called in other files such as MainActivity or MainViewModel.


APIs used:
https://documenter.getpostman.com/view/9648928/SWEB3wHi#getting-started
https://www.weatherapi.com/docs



