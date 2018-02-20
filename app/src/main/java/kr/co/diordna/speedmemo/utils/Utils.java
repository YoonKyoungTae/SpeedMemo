package kr.co.diordna.speedmemo.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import kr.co.diordna.speedmemo.R;

/**
 * Created by ryans on 2018-02-20.
 */

public class Utils {

    public static void sendMailToDeveloper(Context context) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        // email setting 배열로 해놔서 복수 발송 가능
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"yoon91.dev@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.mail_title));
        context.startActivity(email);
    }

    public static void goToReview(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
        context.startActivity(intent);
    }

    public static void shareToApp(Context context) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_msg));

        Intent chooser = Intent.createChooser(intent, context.getString(R.string.share));
        context.startActivity(chooser);
    }
}
