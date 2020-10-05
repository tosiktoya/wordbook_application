package com.example.wordlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends Activity {

	MySQLiteHandler handler;

	EditText editId;
	EditText editWord;
	EditText editMeaning;
	EditText editTag;

	Button btnUpdate;
	Button btnDelete;
	Button btnSelect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		editId = (EditText) findViewById(R.id.editId);
		editWord = (EditText) findViewById(R.id.editWord);
		editMeaning = (EditText) findViewById(R.id.editMeaning);
		editTag = (EditText) findViewById(R.id.editTag);

		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnSelect = (Button) findViewById(R.id.btnSelect);

		Intent intent = getIntent();
		String _id = intent.getStringExtra("_id");

		handler = MySQLiteHandler.open(getApplicationContext());
		Cursor c = handler.selectById(_id);
		startManagingCursor(c);
		if (c.moveToNext()) {

			editId.setText(c.getString(c.getColumnIndex("_id")));
			editWord.setText(c.getString(c.getColumnIndex("Word")));
			editMeaning.setText(c.getString(c.getColumnIndex("Meaning")));
			editTag.setText(c.getString(c.getColumnIndex("Tag")));
		}//

		//
		btnUpdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(1); // update
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(2); // delete

			}
		});

		btnSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}// end onCreate

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		switch (id) {

		case 2: // delete

			AlertDialog d = (AlertDialog) dialog;
			d.setTitle("삭제");
			d.setMessage("정말로 삭제하시겠습니까?");
			break;
		}

		super.onPrepareDialog(id, dialog);
	}

	@Override
	protected Dialog onCreateDialog(final int id) {

		AlertDialog.Builder builder = new AlertDialog.Builder(
				DetailActivity.this);
		builder.setTitle("수정");
		builder.setMessage("정말로 수정하시겠습니까 ?");
		builder.setIcon(android.R.drawable.stat_sys_warning);
		builder.setPositiveButton("확인", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				switch (id) {

				case 1: // update
					String _id = editId.getText().toString();
					String Word = editWord.getText().toString();
					String Meaning = editMeaning.getText().toString();
					String Tag = editTag.getText().toString();

					handler.update(_id, Word, Meaning, Tag);
					break;
				case 2: // delete

					String _id2 = editId.getText().toString();
					handler.delete(_id2);

					break;
				}// end
				finish();
			}
		});
		builder.setNegativeButton("취소", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		return builder.show();
	}// end onCreateDialog

}// end class