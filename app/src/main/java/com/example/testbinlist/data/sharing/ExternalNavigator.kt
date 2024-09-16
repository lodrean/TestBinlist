import android.content.Context
import android.content.Intent
import android.net.Uri


class ExternalNavigator(private val context: Context) {

    fun openLink(link: String) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            Uri.parse(link)/*
            flags = Intent.FLAG_ACTIVITY_NEW_TASK*/
        }
        context.startActivity(Intent.createChooser(shareIntent, null).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    fun openPhone(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:%s".format(phoneNumber))
        }
        context.startActivity(callIntent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    fun openMap(uri: String) {
        val mapIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(uri)
        }
        context.startActivity(Intent.createChooser(mapIntent, null).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}