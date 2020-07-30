def call(String tfAction) {
   sh "terraform ${tfAction} $terraform_path/"
}
