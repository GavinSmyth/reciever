package com.example.x16354406.nfcreader.Parser;

/**
 * Created by x16354406 on 10/04/2018.
 */
import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

import com.ssaurel.nfcreader.R;
import com.ssaurel.nfcreader.model.History;
import com.ssaurel.nfcreader.record.ParsedNdefRecord;
import com.ssaurel.nfcreader.record.SmartPoster;
import com.ssaurel.nfcreader.record.TextRecord;
import com.ssaurel.nfcreader.record.UriRecord;
import com.ssaurel.nfcreader.utils.NFCReaderApp;

import java.util.ArrayList;
import java.util.List;

public class NdefMessageParser {
    private NdefMessageParser() {
    }

    public static List<ParsedNdefRecord> parse(NdefMessage message) {
        return getRecords(message.getRecords());
    }

    public static List<ParsedNdefRecord> getRecords(NdefRecord[] records) {
        List<ParsedNdefRecord> elements = new ArrayList<ParsedNdefRecord>();

        for (final NdefRecord record : records) {
            if (UriRecord.isUri(record)) {
                elements.add(UriRecord.parse(record));
            } else if (TextRecord.isText(record)) {
                elements.add(TextRecord.parse(record));
            } else if (SmartPoster.isPoster(record)) {
                elements.add(SmartPoster.parse(record));
            } else {
                elements.add(new ParsedNdefRecord() {
                    public String str() {
                        return new String(record.getPayload());
                    }
                });
            }
        }

        return elements;
    }
}
}
