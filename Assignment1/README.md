# Instructions for submitting the code for COSC201 Assignment 1

On GitLab (altitude.otago.ac.nz)
1. Create a Blank Project
   - (Using your own usercode instead of "stuad123")
   - namespace = stuad123
   - project slug = 201-a1-stuad123
   - visibility = private
   - Create Project
2. Go to Settings -> Repository
   - Protected Branches -> Expand
   - Scroll down to "main `default`"
   - Click "Unprotect"
   - Confirm "Unprotect Branch"
3. On your machine containing your assignment code
   - `cd existing_repo` (note the commands below use "a1" not "origin")
   - `git remote add a1 https://altitude.otago.ac.nz/stuad123/201-a1-stu123`
   - `git branch -M main`
   - `git push -f a1`
4. On GitLab
   - Project information -> Members
   - Click "Invite members"
   - Select Role -> Maintainer
   - Username -> hewia06p
   - Click "Invite"
   
If you have any difficulties Iain or one of the demonstrators will be happy to help out.
