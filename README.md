# hashicorp


Spring Vault(HASHICORP VAULT)

Spring Vault provides client-side support for accessing, storing and revoking secrets. 
With HashiCorp’s Vault you have a central place to manage external secret data for applications across all environments.
Vault can manage static and dynamic secrets such as application data, username/password for remote applications/resources and provide credentials for external services such as MySQL, PostgreSQL, Apache Cassandra, Consul, AWS and more.


Getting Help

For a comprehensive treatment of all the Spring Vault features, please refer to:

the User Guide

the JavaDocs have extensive comments in them as well.

the home page of Spring Vault contains links to articles and other resources.

for more detailed questions, use Spring Vault on Stackoverflow.

Vault Setup:

1)Installed Vault.


Vault Server Run:
   # vault server -dev
   
   export VAULT_ADDR='http://127.0.0.1:8200'
   
   Vault Initilazation: # vault init
   
   Error initializing Vault: Error making API request.
   URL: PUT http://127.0.0.1:8200/v1/sys/init
   Code: 400. Errors:

* Vault is already initialized

   Vault status:
   # vault status
    Sealed: false
    Key Shares: 1
    Key Threshold: 1
    Unseal Progress: 0
    Version: 0.6.2
    Cluster Name: vault-cluster-93cfd7dd
    Cluster ID: 6a156877-4f74-3c50-f4b3-bbb7087cd20f
    
    Vault write:
    # vault write secret/hashicorp-vault example.username=spring example.password=password
    Success! Data written to: secret/hashicorp-vault

    Vault read:
    # vault read secret/hashicorp-vault
    Key             	Value
      ---             	-----
      refresh_interval	768h0m0s
      password        	password
      username        	spring
      
      Vault Authentication: 
      #vault auth <Token>
       vault auth 7ca53aa7-18f8-89b9-d11c-ddb955669ac0
       
       After that i was configured the Vault token and configuration in my spring boot application bootstrap.yml file.
        spring:
          application:
            name: hashicorp-vault
          spring: 
          cloud:
            vault: 
              scheme: http
              host: 127.0.0.1
              port: 8200
              token: 30372901-ce31-c82e-f0d8-70553333e1a2
              fail: true
              
           After that i was configured two datasources(MYSQL, POSTGRESQL) in my student service.
           Based on Student Gender data will be saved in particular database.
    
    
    

   
   

