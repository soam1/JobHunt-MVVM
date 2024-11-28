JobHunt-MVVM/
├── api/
│ ├── JobsApiServiceInterface.kt
│ └── RetrofitInstance.kt
├── database/
│ ├── JobDao.kt
│ ├── JobDatabase.kt
│ └── entities/
│ └── JobEntity.kt
├── repository/
│ └── JobsRepository.kt
├── ui/
│ ├── adapter/
│ │ └── JobAdapter.kt
│ ├── fragments/
│ │ ├── JobsFragment.kt
│ │ ├── JobDetailFragment.kt
│ │ └── BookmarksFragment.kt
│ ├── viewmodel/
│ │ └── JobsViewModel.kt
│ └── MainActivity.kt
├── utils/
│ └── LoadingState.kt
├── res/
│ ├── layout/
│ │ ├── fragment_jobs.xml
│ │ ├── fragment_job_detail.xml
│ │ ├── fragment_bookmarks.xml
│ │ ├── item_job.xml
│ │ └── activity_main.xml
│ ├── navigation/
│ │ └── nav_graph.xml
│ ├── values/
│ │ ├── colors.xml
│ │ ├── strings.xml
│ │ └── styles.xml
├── .gitignore
├── build.gradle
├── settings.gradle
└── README.md

# JobHunt-MVVM

JobHunt-MVVM is an Android application built with the MVVM architecture. The app allows users to
browse job listings fetched from a remote API, view job details, and bookmark jobs for offline
viewing. It also demonstrates the use of modern Android development tools and libraries such as
Retrofit, Room, Coroutines, ViewBinding, and Navigation Component.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Screenshots](#screenshots)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Job Listing:** Fetch jobs from an API and display them in a paginated RecyclerView.
- **Job Details:** View detailed information about a job, including title, location, salary, and
  contact details.
- **Bookmarking:** Bookmark jobs for later reference, with data stored locally using Room for
  offline access.
- **Navigation:** Seamlessly navigate between the Jobs and Bookmarks sections using a bottom
  navigation bar.
- **State Management:** Handle loading, error, and empty states throughout the app.

## Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern:

- **Model:** Represents the data and business logic (e.g., `JobEntity`, `JobsRepository`).
- **View:** The UI layer, which observes data from the ViewModel and updates the
  UI (`JobsFragment`, `JobDetailFragment`).
- **ViewModel:** Manages UI-related data and communicates with the repository to fetch
  data (`JobsViewModel`).

## Screenshots

![Jobs Screen](link-to-screenshot-1)
![Job Details Screen](link-to-screenshot-2)
![Bookmarks Screen](link-to-screenshot-3)

## Technologies Used

- **Kotlin:** Programming language.
- **Retrofit:** For making API calls.
- **Room:** For local data persistence.
- **Coroutines:** For background processing.
- **ViewBinding:** For binding UI components.
- **Navigation Component:** For handling in-app navigation.
- **RecyclerView:** For displaying lists of data with pagination.

## Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/soam1/JobHunt-MVVM.git
   cd JobHunt-MVVM
   ```

2. **Open the project in Android Studio.**

3. **Build the project:**
    - Ensure all dependencies are downloaded and synced.

4. **Run the application:**
    - Connect an Android device or use an emulator.

## Usage

- **Browsing Jobs:** Open the app to view the latest job listings.
- **Viewing Job Details:** Tap on a job to see more detailed information.
- **Bookmarking Jobs:** Click the bookmark icon in the job details to save a job for offline
  viewing.
- **Viewing Bookmarks:** Switch to the Bookmarks tab to see all your saved jobs.

## Video Demo

[![Video Demo](./job_hunt_mvvm_demo_vdo.mp4)]


## Contributing

Contributions are welcome! Please fork this repository and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
