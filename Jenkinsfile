node {
    def mvnHome
	
    stage('Checkout') { 
        checkout scm
        mvnHome = tool 'M3'
    }
	
	stage('Smoke') {
	   dir ('FileNew/serenity-rest-books') {
			if (isUnix()) {
				sh "'${mvnHome}/bin/mvn' clean verify -Dcucumber.options=\"--tags @smoke\""
			} else {
				bat "${mvnHome}\\bin\\mvn clean verify -Dcucumber.options=\"--tags @smoke\""
			}
			publishHTML(target: [
				reportName : 'Smoke',
				reportDir:   'target/site/serenity',
				reportFiles: 'index.html',
				keepAll:     true,
				alwaysLinkToLastBuild: true,
				allowMissing: false
			])
		}
    }
	
    stage('REST') {
	    dir ('FileNew/serenity-rest-books') {
			try {
				if (isUnix()) {
					sh "'${mvnHome}/bin/mvn' clean verify"
				} else {
					bat "${mvnHome}\\bin\\mvn clean verify"
				}
			} catch (err) {

			} finally {
					publishHTML(target: [
						reportName : 'REST',
						reportDir:   'target/site/serenity',
						reportFiles: 'index.html',
						keepAll:     true,
						alwaysLinkToLastBuild: true,
						allowMissing: false
				])
			}
		}
    }
   
    stage('Results') {
        junit '**/target/failsafe-reports/TEST-*.xml'
    }  
}
