package in.kmit.MyJavaProject;

public class Calculator {

    // Method to add two numbers
    public int add(int a, int b) {
        return a + b;
    }

    // Method to subtract two numbers
    public int subtract(int a, int b) {
        return a - b;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        int num1 = 10;
        int num2 = 5;

        int sum = calc.add(num1, num2);
        int difference = calc.subtract(num1, num2);

        System.out.println("Addition: " + sum);
        System.out.println("Subtraction: " + difference);
    }
}

aws.txt
    in chrome type ==> aws student login ==> login with ur credentials
1. click on courses ==> click on modules
2. Scroll down and select Launch AWS Academy Lab
3. click on start lab( red turns to green)
4. then click on that aws green dot
5. click on ec2 instance
6. Click on Launch Instance
7. give name (e.g: MyWebServer), select option ubuntu ==> Instance type t2.micro
8. Create a new keypair---> Give KeyPair name and click on create key pair ==> a keypair will downloaded  with extension .pem ==> Store key      in folder AWS which shud be created on desktop
9. click all check boxes(http and https)
10. Storage - 8GB
11. click on launch instance
12. Now click on the instances ==> Wait for the Success message to apper in green color
13. You have to get 2 tests passes. 
Important:---- Now check the box and click on connect
14.Now you can connect local system to server (EC2 instance) using secure shell SSH ==> go to shh client, copy the ssh command.
15. Copy the path of .pem file that you saved earlier in folder AWS 
16. Open Powershell in administrative mode and navigate to that path. 
     Type:   cd < path> 
17. Go to SSh and copy the command ssh and paste it in powershell==>type yes
18. * sudo apt update
    * sudo apt-get install docker.io
    * sudo apt install git
    * sudo apt install nano
19. Create another folder on desktop and Create basic index.html file in folder Example and save it
20. Open git Bash in folder Example by right clicking with mouse
    * git init
    * git add .
    * git commit –m “first commit”
21. Create git repository (here with name AWS)
   * git branch –M main
   * git remote add origin <https url>
   * git push –u origin main
22. Copy http path of the repo
23 In the powershell inside ubuntu type this ==>git clone <copied http url>
   * cd AWS
   * ls 
   * nano Dockerfile
 --> paste these (for index.html) ==> 
   FROM nginx:alpine
   COPY . /usr/share/nginx/html
 --> (for mavenweb project paste) ==> 
  FROM tomcat:9-jdk11
  COPY target/*.war /usr/local/tomcat/webapps 
==>ctrl+s and ctrl+x
24. Build docker image by executing the following command
   * sudo docker build –t mywebapp .
25 run the image
 * sudo docker run –d –p 80:80 mywebapp
26. copy public ipv4 address
:  Paste it in the browser to get below output
Note : if https :__ won’t work then type just http:___
27. Run the following command to stop the container
 * sudo docker ps
 * sudo docker stop <container-id>
28.Go back to instances and click on option instance. Once load as below, then Click on Instance state and select terminate instances
29. Click on Terminated (deleted)==> Now click on End lab

for web after 18 direct go to 23






jenkins_java_webs
    I.	Steps for MavenJava Automation:
Step 1: Open Jenkins (localhost:8080)
   	 ├── Click on "New Item" (left side menu
Step 2: Create Freestyle Project (e.g., MavenJava_Build)
        	├── Enter project name (e.g., MavenJava_Build)
        	├── Click "OK"
└── Configure the project:
            		├── Description: "Java Build demo"
            		├── Source Code Management:
            			└── Git repository URL: [GitMavenJava repo URL]
            		├── Branches to build: */Main   or  */master
  		└── Build Steps:
               	     ├── Add Build Step -> "Invoke top-level Maven targets"
                  		└── Maven version: MAVEN_HOME
                 		└── Goals: clean
                	├── Add Build Step -> "Invoke top-level Maven targets"
                		└── Maven version: MAVEN_HOME
                		└── Goals: install
                	└── Post-build Actions:
                    	       ├── Add Post Build Action -> "Archive the artifacts"
                    			└── Files to archive: **/*
                    	     ├── Add Post Build Action -> "Build other projects"
                    			└── Projects to build: MavenJava_Test
                    			└── Trigger: Only if build is stable
                    	     └── Apply and Save

└── Step 3: Create Freestyle Project (e.g., MavenJava_Test)
        	├── Enter project name (e.g., MavenJava_Test)
        	├── Click "OK"
              └── Configure the project:
             ├── Description: "Test demo"
             ├── Build Environment:
            		└── Check: "Delete the workspace before build starts"
            ├── Add Build Step -> "Copy artifacts from another project"
            		└── Project name: MavenJava_Build
            		└── Build: Stable build only  // tick at this
            		└── Artifacts to copy: **/*
            ├── Add Build Step -> "Invoke top-level Maven targets"
            		└── Maven version: MAVEN_HOME
            		└── Goals: test
            		└── Post-build Actions:
                ├── Add Post Build Action -> "Archive the artifacts"
                	└── Files to archive: **/*
                	└── Apply and Save

└── Step 4: Create Pipeline View for Maven Java project
        ├── Click "+" beside "All" on the dashboard
        ├── Enter name: MavenJava_Pipeline
        ├── Select "Build pipeline view"         // tick here
         |--- create
        └── Pipeline Flow:
            ├── Layout: Based on upstream/downstream relationship
            ├── Initial job: MavenJava_Build
            └── Apply and Save OK

└── Step 5: Run the Pipeline and Check Output
        ├── Click on the trigger to run the pipeline
        ├── click on the small black box to open the console to check if the build is success
            Note : 
1.	If build is success and the test project is also automatically triggered with name       
                      “MavenJava_Test”
2.	The pipeline is successful if it is in green color as shown ->check the console of the test project
3.	The test project is successful and all the artifacts are archived successfully



II. Maven Web Automation Steps:

└── Step 1: Open Jenkins (localhost:8080)
    ├── Click on "New Item" (left side menu)
    
└── Step 2: Create Freestyle Project (e.g., MavenWeb_Build)
        ├── Enter project name (e.g., MavenWeb_Build)
        ├── Click "OK"
        └── Configure the project:
            ├── Description: "Web Build demo"
            ├── Source Code Management:
            		└── Git repository URL: [GitMavenWeb repo URL]
            ├── Branches to build: */Main or master
            └── Build Steps:
                ├── Add Build Step -> "Invoke top-level Maven targets"
                	└── Maven version: MAVEN_HOME
                	 └── Goals: clean
                ├── Add Build Step -> "Invoke top-level Maven targets"
                	└── Maven version: MAVEN_HOME
                	└── Goals: install
                └── Post-build Actions:
                    ├── Add Post Build Action -> "Archive the artifacts"
                   	 └── Files to archive: **/*
                    ├── Add Post Build Action -> "Build other projects"
                    	└── Projects to build: MavenWeb_Test
                    	└── Trigger: Only if build is stable
                    └── Apply and Save

└── Step 3: Create Freestyle Project (e.g., MavenWeb_Test)
        ├── Enter project name (e.g., MavenWeb_Test)
        ├── Click "OK"
        └── Configure the project:
            ├── Description: "Test demo"
            ├── Build Environment:
            		└── Check: "Delete the workspace before build starts"
            ├── Add Build Step -> "Copy artifacts from another project"
            		└── Project name: MavenWeb_Build
            		└── Build: Stable build only
            		└── Artifacts to copy: **/*
            ├── Add Build Step -> "Invoke top-level Maven targets"
           		└── Maven version: MAVEN_HOME
            		└── Goals: test
            └── Post-build Actions:
                ├── Add Post Build Action -> "Archive the artifacts"
                	└── Files to archive: **/*
                ├── Add Post Build Action -> "Build other projects"
                	└── Projects to build: MavenWeb_Deploy
                └── Apply and Save

└── Step 4: Create Freestyle Project (e.g., MavenWeb_Deploy)
        ├── Enter project name (e.g., MavenWeb_Deploy)
        ├── Click "OK"
        └── Configure the project:
            ├── Description: "Web Code Deployment"
            ├── Build Environment:
            		└── Check: "Delete the workspace before build starts"
            ├── Add Build Step -> "Copy artifacts from another project"
            		└── Project name: MavenWeb_Test
            		└── Build: Stable build only
               	└── Artifacts to copy: **/*
            └── Post-build Actions:
                ├── Add Post Build Action -> "Deploy WAR/EAR to a container"
   └── WAR/EAR File: **/*.war
   └── Context path: Webpath
 └── Add container -> Tomcat 9.x remote
└── Credentials: Username: admin, Password: 1234
── Tomcat URL: https://localhost:8085/
                └── Apply and Save

└── Step 5: Create Pipeline View for MavenWeb
        ├── Click "+" beside "All" on the dashboard
        ├── Enter name: MavenWeb_Pipeline
        ├── Select "Build pipeline view"
        └── Pipeline Flow:
            ├── Layout: Based on upstream/downstream relationship
            ├── Initial job: MavenWeb_Build
            └── Apply and Save

    └── Step 6: Run the Pipeline and Check Output
        ├── Click on the trigger “RUN” to run the pipeline
            Note: 
1.	After Click on Run -> click on the small black box to open the console to check if the build is success
2.	Now we see all the build has  success if it appears in green color
        ├── Open Tomcat homepage in another tab
        ├── Click on the "/webpath" option under the manager app





    minikube.txt
    ** NOTE ** ==> open new powershells if you can't see paths of your systems so as to execute the next following commands.
Start Minikube (Background docker should be running)
  1. Open a terminal (PowerShell or CMD). Do the following commands
  2. Start
      * If (minikube start) doesn't work type below cmnd
      *	(minikube start --driver=docker) 
      
3. Verify Minikube is running:
   * minikube status
4. Interact with Minikube
   * minikube kubectl -- get pods -A
5. Run the following to verify the installation:
   * kubectl version --client
6. Creating deployment
   * kubectl create deployment mynginx --image=nginx
   * not mandatory==> if already created then 
     kubectl set image deployment/myngnix nginx=nginx:latest
7. Verify the deployment using: Kubernetes responds by showing you a list that includes the names of your deployment groups
   * kubectl get deployments
   output: Ensure mynginx appears in the output
8. * kubectl get pods

   * kubectl describe pods
   * kubectl get services
9. Expose Deployment as a Service:
  * kubectl expose deployment mynginx --type=NodePort --port=80 --target-port=80
10. Scale the Deployment
  * kubectl scale deployment mynginx --replicas=4
  * kubectl get service myngnix
  * kubectl get pods
11. Access the Nginx App
  2* minikube service mynginx --url ==> if u type this, the output will be a url, u paste in chrome, nginx app will be opened and explore       it (or) 
  1* kubectl port-forward svc/mynginx 8081:80 ==> Access the app via http://localhost:8081.
12. New powershell==> Open the minikube dashboard
  * minikube dashboard 
  output: automatically kubernets dashboard will be opened with three green circles, u need to wait for sometime for this and explore it.
13. Stop Nginx Deployment:(in new)
  * kubectl delete deployment mynginx
  * kubectl delete service mynginx
14. Stop Minikube (Optional):
   * minikube stop
15. Delete Minikube Cluster (Optional):
    * minikube delete



    nagios.txt
    starting basic steps:(background docker must run)
* docker --version
* docker ps
1. Pull the Nagios Docker Image
  • Open powershell and run:
  * docker pull jasonrivers/nagios:latest
2. Run Nagios
  * docker run --name nagiosdemo -p 8888:80 jasonrivers/nagios:latest
  output: it will show successfully launched (takes time)
3. Access Nagios Dashboard
   • Open your browser and navigate to:
   * http://localhost:8888
4. Login Credentials:
    Username: nagiosadmin
    Password: nagios	
5. Once logged in, explore:
      	Hosts: View systems being monitored.
      	Services: Check tasks being monitored (e.g., CPU usage).
      	Alerts: Access recent notifications.
6. Stop and Remove Nagios
   => To Stop the Container:
   * docker stop nagiosdemo
   => Delete the Container:
   * docker rm nagiosdemo
7. Delete the Nagios image:
  * docker rmi jasonrivers/nagios:latest
 







 script.txt
    script
+ new item -script name
pipeline
in triggers click build periodically 
* * * * *
paste script 
in script go to build


pipeline {
    agent any
    tools{
        maven 'MAVEN_HOME'
    }
    stages {
        stage('git repo & clean') {
            steps {
                bat "rmdir  /s /q octYogitaJava || exit 0"
                bat "git clone https://github.com/anujyog1/octYogitaJava.git"
                bat "mvn clean -f octYogitaJava"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f octYogitaJava"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f octYogitaJava"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f octYogitaJava"
            }
        }
    }
}







Week 9: Pipeline Creation using script Last year fie
1.	Evaluation of Jenkins pipeline.
2.	WORKING ON BUILD TRIGGERS FOR LAST JENKINS PIPILINE
3.	Hands-on practice on creation of scripted Jenkins pipeline.
4.	Take  the screenshots for above task 


Procedure
General :
 	Description :-  provide the description of the project
	Build triggers:  here we can provide with build triggers of you choice
Advance project option:
	Definition: 
		Choose pipeline script
 
Here code for pipeline -script
	
Copy the code there
-------------------------------------------------------------------------------------------------------------------------
pipeline {
    agent any
    tools{
        maven 'MAVEN-HOME'
    }
    stages {
        stage('git repo & clean') {
            steps {
                //bat "rmdir  /s /q mavenjava"
                bat "git clone provide your github link"
                bat "mvn clean -f mavenjava"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f mavenjava" #project name#
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f mavenjava"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f mavenjava"
            }
        }
    }
}
Apply and save

Other example
pipeline {
    agent any
    tools{
        maven 'MAVEN_HOME'
    }
    stages {
        stage('git repo & clean') {
            steps {
                //bat "rmdir  /s /q mavenjava"
                bat "git clone https://github.com/anujyog1/octYogitaJava.git"
                bat "mvn clean -f octYogitaJava"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f octYogitaJava"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f octYogitaJava"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f octYogitaJava"
            }
        }
    }
}

pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME' // Ensure Maven is configured correctly in Jenkins
    }
    stages {
        stage('Git Clone and Clean') {
            steps {
                script {
                    try {
                        // Use absolute paths to avoid dependency on configurations
                        bat 'C:\\Windows\\System32\\cmd.exe /c rmdir /s /q SampleMavenJavaProject'
                        bat 'C:\\Windows\\System32\\cmd.exe /c git clone https://github.com/budarajumadhurika/SampleMavenJavaProject.git'
                    } catch (Exception e) {
                        echo "Git clone or clean failed: ${e}"
                    }
                }
            }
        }
        stage('Maven Clean') {
            steps {
                script {
                    try {
                        bat '"C:\\Program Files\\apache-maven-3.9.9\\bin\\mvn.cmd" clean -f SampleMavenJavaProject'
                    } catch (Exception e) {
                        echo "Maven clean failed: ${e}"
                    }
                }
            }
        }
        stage('Maven Install') {
            steps {
                script {
                    try {
                        bat '"C:\\Program Files\\apache-maven-3.9.9\\bin\\mvn.cmd" install -f SampleMavenJavaProject'
                    } catch (Exception e) {
                        echo "Maven install failed: ${e}"
                    }
                }
            }
        }
        stage('Maven Test') {
            steps {
                script {
                    try {
                        bat '"C:\\Program Files\\apache-maven-3.9.9\\bin\\mvn.cmd" test -f SampleMavenJavaProject'
                    } catch (Exception e) {
                        echo "Maven test failed: ${e}"
                    }
                }
            }
        }
        stage('Maven Package') {
            steps {
                script {
                    try {
                        bat '"C:\\Program Files\\apache-maven-3.9.9\\bin\\mvn.cmd" package -f SampleMavenJavaProject'
                    } catch (Exception e) {
                        echo "Maven package failed: ${e}"
                    }
                }
            }
        }
    }
}



node {
    stage('Checkout') {
        echo "Checking out source code..."
    }

    stage('Build') {
        echo "Building the project..."
    }

    stage('Test') {
        echo "Running tests..."
    }

    stage('Deploy') {
        echo "Deploying..."
    }
}

