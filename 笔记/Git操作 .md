一般开发的分支叫dev

我们一般clone下来后，还要自己建个本地分支用来开发，不能直接在原来clone的dev分支上开发：

`git checkout -b 分支名`  这个命令是创建并切换到新建的分支上。

`git branch`查看本地所有分支，并且当前处于哪个分支。

可以用`git diff`命令查看你现在写的代码和暂存区的代码有什么改动。

`git add . `将修改的文件添加进暂存区。

`git pull `等价于fetch和merge两步。如果你在开发的时候，别人已经有新的提交了，这时远程代码和当前文件夹下的代码就不一样了，所以要拉取。

`git commit`

合并代码的时候要先切换到真正的dev分支`git checkout dev`，然后`git merge 开发的分支`

`git push`

`git branch -D 分支名`完成开发并且合并之后，就可以删除当前的分支了。