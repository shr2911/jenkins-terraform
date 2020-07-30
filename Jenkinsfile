@Library('cicd-pipeline')_

pipeline {
    agent any
    
parameters {
        string(name: 'terraform_path', defaultValue: 'terraform', description: 'Enter a terraform path, like terraform/test')
  }
    stages {

     stage("Terraform Init") {
        when {
          expression {
              env.BRANCH_NAME == "dev" || env.BRANCH_NAME == "master"
          }
       }

        steps {
            script {
            def tfHome = tool name: 'Terraform'
            env.PATH = "${tfHome}:${env.PATH}"
            
            echo 'terraform init'
           // sh "${env.TERRAFORM_HOME}/terraform init terraform/"
              def config = "init"
              terraformPipeline(config)     
        }
        }
   }
       stage("Terraform Validate") {

         steps {
              echo "terraform validate"
             terraformPipeline("config")
             //sh "terraform validate $terraform_path/"
        }
}
        stage("Terraform Plan") {

          steps {
                echo "terraform plan"
              terraformPipeline("config")
             // sh "terraform plan $terraform_path/"
         }
}
         stage("Terraform Apply") {

           steps {
              echo "terraform apply"
               terraformPipeline("config")
               //sh "terraform apply -auto-approve $terraform_path/"
          }
     }
  }
}

