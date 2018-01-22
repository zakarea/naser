# vanet.autonom.privacy


Command line instructions

Git global setup
git config --global user.name "USER_NAME
git config --global user.email "EMAIL"

Create a new repository
git clone https://github.com/zakarea/vanet.autonom.privacy.git
cd CoreW3
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master

Existing folder
cd existing_folder
git init
git remote add origin https://github.com/zakarea/vanet.autonom.privacy.git
git add .
git commit -m "Initial commit"
git push -u origin master

Existing Git repository
cd existing_repo
git remote rename origin old-origin
git remote add origin https://github.com/zakarea/vanet.autonom.privacy.git
git push -u origin --all
git push -u origin --tags
