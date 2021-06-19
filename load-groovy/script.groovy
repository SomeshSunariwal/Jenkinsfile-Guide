def buildApp() {
    echo "Build"
}

def testApp() {
    echo "Test"
}

def releaseApp() {
    echo "Release ${params.NAME}"
}

return this