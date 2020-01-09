package com.bycrr.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.bycrr.festafimdeano.R;
import com.bycrr.festafimdeano.constant.FimDeAnoConstants;
import com.bycrr.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

  private ViewHolder mViewHolder = new ViewHolder();
  private SecurityPreferences mSecurityPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    this.mSecurityPreferences = new SecurityPreferences(this);
    this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
    this.mViewHolder.checkParticipate.setOnClickListener(this);
    this.loadDataFromAcvity();
  }

  @Override
  public void onClick(View v) {

    if (v.getId() == R.id.check_participate) {

      if (this.mViewHolder.checkParticipate.isChecked()) {
        // salvar a presença
        this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);

      } else {
        // salvar a ausencia
        this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
      }
    }
  }

  private void loadDataFromAcvity() {
    Bundle extras = getIntent().getExtras();

    if (extras != null) {
      String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);

      if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)) {
        this.mViewHolder.checkParticipate.setChecked(true);

      } else {
         this.mViewHolder.checkParticipate.setChecked(false);
      }
    }
  }

  private static class ViewHolder {
    CheckBox checkParticipate;
  }
}
