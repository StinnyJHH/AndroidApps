package course.labs.notificationslab

internal interface DownloadFinishedListener {
    open fun notifyDataRefreshed(feeds: Array<String?>?)
}