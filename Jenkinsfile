@Library('cicd-pipeline') _

def config = [
    branchMatcher: [RELEASE: ['master'], DEVELOPMENT: ['^(?!master$).*$']],
    pra: [
        sdbPath: 'app/location/pra',
        userNameKey: 'pra_username',
        passwordKey: 'pra_password'

    ],
    deploymentEnvironment: [
        'test-raw-sink': [
            deployFlow: [
                DEVELOPMENT:  ['Pre-Deploy Hook', 'Init', 'Plan'],
                PULL_REQUEST: ['Pre-Deploy Hook', 'Init', 'Plan'],
                RELEASE:      ['Pre-Deploy Hook', 'Init', 'Plan', 'Confirm Apply', 'Apply'],
                DESTROY:      ['Pre-Deploy Hook', 'Init', 'Destroy']
            ],
            tags: [
                'nike-environment': 'QA',
            ],
            preDeployHook: [
                build: [
                    cmd: "cd terraform/data-maturity/raw/sink " +
                    "&& curl 'https://epctl.platforms.nike.com/binaries/latest/epctl_linux_amd64' -o 'epctl' " +
                    "&& chmod +x ./epctl " +
                    "&& ./epctl terraform setup-providers --local"
                ]
            ],
            terraform: [
                aws: [
                    region: 'us-west-2',
                    role: 'jenkins-role',
                    roleAccount: '472012226690',
                ],
                backendConfig: [
                    bucket: 'global-artifacts-us-west-2-472012226690',
                    key: 'location-management-infrastructure/qa/raw/sink/terraform.tfstate',
                    region: 'us-west-2',
                ],
                workingDir: 'terraform/data-maturity/raw/sink',
                varFiles: [
                    'non-prod.tfvars'
                ]
            ],
        ]
        ]
   ]
   
   terraformPipeline(config)
