## 🚧 <span style="color: #3498DB;">Work In Progress (WIP)</span>

This README is still a work in progress.  
It will be updated with more detailed information and examples soon.  
Feel free to contribute or check back for further improvements!

# <span style="color: #3498DB;">TrelloBridge</span> - Java Wrapper for the Trello API

**TrelloBridge** is a lightweight and powerful **Java API** designed to simplify integration with **Trello**.  
It provides a seamless way to interact with Trello's features and automate workflows, reducing the complexities of API
calls.

### 🚀 <span style="color: #3498DB;">Key Features</span>

- **Effortless API Integration**: Quickly integrate with the Trello API with minimal setup.
- **Comprehensive Board Management**: Easily create and modify boards, lists, and cards.
- **Full Customization**: Leverage Trello features like labels, due dates, checklists, and members for tailored
  workflows.
- **Ideal for Automation**: Perfect for automating project management tasks and team collaboration.

### 🛠️ <span style="color: #3498DB;">Installation</span>

To integrate **TrelloBridge** into your Java project, follow the steps below:

#### Using Maven

Add the following dependency to your `pom.xml` file:

```xml

<dependency>
    <groupId>me.prexorjustin</groupId>
    <artifactId>trellobridge</artifactId>
    <version>1.0.0</version>
</dependency>
```

Then, run `mvn install` to download and include the dependency in your project.  
<br>

#### Using Gradle

Add the following line to your `build.gradle` file under `dependencies`:

```gradle
implementation 'me.prexorjustin:trellobridge:1.0.0'
```

Afterwards, sync your Gradle project to include the dependency.
<br>

### 📄 <span style="color: #3498DB;">Documentation</span>

For detailed documentation and usage examples, visit
the [TrelloBridge Wiki](https://github.com/PrexorJustin/TrelloBridge/wiki).  
The Wiki contains full instructions on how to make the most of TrelloBridge's features.

### 🧑‍💻 <span style="color: #3498DB;">Contributing</span>

We welcome contributions to improve and expand **TrelloBridge**! Here's how you can contribute:

- **Fork** the repository and submit pull requests.
- **Report** bugs or suggest new features.
- **Improve** the documentation.

Make sure your contributions are well-documented and thoroughly tested.

### 📜 <span style="color: #3498DB;">License</span>

TrelloBridge is licensed under the MIT License.  
For more details, see the [LICENSE](https://github.com/PrexorJustin/TrelloBridge/blob/master/LICENSE) file.

### 👏 <span style="color: #3498DB;">Acknowledgments</span>

I would like to thank [Trello-Java-Wrapper](https://github.com/taskadapter/trello-java-wrapper) for inspiring
TrelloBridge. Its innovative approach to API management and data handling provided valuable insights into how to
structure and manage integrations effectively.

A special thanks to the creators for their excellent work and the inspiration they provided for this project.

<br>

<table style="width: 100%; border-collapse: collapse;">
  <thead>
    <tr style="background-color: #2980b9; color: white; font-weight: bold; text-align: left;">
      <th style="padding: 15px;">API Domain</th>
      <th style="padding: 15px; text-align: center;">Implemented (✔/🚧/⌛)</th>
      <th style="padding: 15px;">Description</th>
      <th style="padding: 15px;">Notes</th>
    </tr>
  </thead>
  <tbody>
    <tr style="background-color: #34495e; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Boards</td>
      <td style="padding: 12px; text-align: center; color: green;">✔</td>
      <td style="padding: 12px;">API calls to create, update, and manage boards on Trello.</td>
      <td style="padding: 12px;">Fully implemented. <br> Pending documentation.</td>
    </tr>
    <tr style="background-color: #2c3e50; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Actions</td>
      <td style="padding: 12px; text-align: center;">🚧</td>
      <td style="padding: 12px;">API calls to manage actions within boards, cards and lists.</td>
      <td style="padding: 12px;">Basic layout finished. working on implementing API routes</td>
    </tr>
    <tr style="background-color: #34495e; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Members</td>
      <td style="padding: 12px; text-align: center;">🚧</td>
      <td style="padding: 12px;">API calls to manage members within boards and cards.</td>
      <td style="padding: 12px;">Basic Member functionality finished.</td>
    </tr>
    <tr style="background-color: #2c3e50; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Cards</td>
      <td style="padding: 12px; text-align: center;"><span style="color: grey;">🚧</span></td>
      <td style="padding: 12px;">API calls to create, manage, and update cards.</td>
      <td style="padding: 12px;">Not started yet. Pending initial planning and design.</td>
    </tr>
    <tr style="background-color: #34495e; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Lists</td>
      <td style="padding: 12px; text-align: center;"><span style="color: grey;">🚧</span></td>
      <td style="padding: 12px;">API calls to manage lists within boards.</td>
      <td style="padding: 12px;">Not started yet. Pending initial planning and design.</td>
    </tr>
    <tr style="background-color: #2c3e50; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Checklists</td>
      <td style="padding: 12px; text-align: center;"><span style="color: grey;">🚧</span></td>
      <td style="padding: 12px;">API calls for creating and managing checklists within cards.</td>
      <td style="padding: 12px;">Not started yet. Pending initial planning and design.</td>
    </tr>
    <tr style="background-color: #34495e; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Webhooks</td>
      <td style="padding: 12px; text-align: center;"><span style="color: grey;">⌛</span></td>
      <td style="padding: 12px;">API calls to create and manage webhooks for monitoring Trello data.</td>
      <td style="padding: 12px;">Not started yet. Pending initial planning and design.</td>
    </tr>
    <tr style="background-color: #2c3e50; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Labels</td>
      <td style="padding: 12px; text-align: center;"><span style="color: grey;">🚧</span></td>
      <td style="padding: 12px;">API calls to manage labels on cards and boards.</td>
      <td style="padding: 12px;">Not started yet. Pending initial planning and design.</td>
    </tr>
    <tr style="background-color: #34495e; border-bottom: 1px solid #ddd; color: white;">
      <td style="padding: 12px;">Organizations</td>
      <td style="padding: 12px; text-align: center;"><span style="color: grey;">⌛</span></td>
      <td style="padding: 12px;">API calls to manage teams and members within organizations.</td>
      <td style="padding: 12px;">Not started yet. Pending initial planning and design.</td>
    </tr>
  </tbody>
</table>

<p><strong>Legend:</strong></p>
<ul>
  <li><span style="color: green;">[✔]</span> - Fully implemented</li>
  <li><span style="color: orange;">[🚧]</span> - Work in Progress</li>
<li><span style="color: grey;">[⌛]</span> - Not yet implemented</li>
</ul>