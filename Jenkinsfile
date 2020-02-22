node {
    def mvnHome
	
    stage('Checkout') { 
        checkout scm
        mvnHome = tool 'M3'
    }
	
	stage('Smoke') {
		if (isUnix()) {
			sh "'${mvnHome}/bin/mvn' clean verify -Dcucumber.options=\"--tags @smoke\""
		} else {
			bat "${mvnHome}\\bin\\mvn clean verify -Dcucumber.options=\"--tags @smoke\""
		}
		publishReport('Smoke')
    }
	
    stage('REST') {
		try {
			if (isUnix()) {
				sh "'${mvnHome}/bin/mvn' clean verify"
			} else {
				bat "${mvnHome}\\bin\\mvn clean verify"
			}
		} catch (err) {

		} finally {
			publishReport('REST')
		}
    }
   
    stage('Results') {
        junit '**/target/failsafe-reports/TEST-*.xml'
    }  
}

def publishReport(name) {
	publishHTML(target: [
		reportName:  name,
		reportDir:   'target/site/serenity',
		reportFiles: 'index.html',
		keepAll:     true,
		alwaysLinkToLastBuild: true,
		allowMissing: false
	])
}