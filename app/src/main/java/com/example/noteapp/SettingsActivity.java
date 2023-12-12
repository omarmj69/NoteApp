import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private SeekBar seekBarVolume;
    private Switch switchSound;
    private RadioGroup radioGroupTheme;
    private EditText editTextFont;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        seekBarVolume = findViewById(R.id.seekBarVolume);
        switchSound = findViewById(R.id.switchSound);
        radioGroupTheme = findViewById(R.id.radioGroupTheme);
        editTextFont = findViewById(R.id.editTextFont);
        buttonSave = findViewById(R.id.buttonSave);

        // Load saved preferences
        seekBarVolume.setProgress(sharedPreferences.getInt(KEY_VOLUME, 50));
        switchSound.setChecked(sharedPreferences.getBoolean(KEY_SOUND, true));
        checkRadioButton(sharedPreferences.getInt(KEY_THEME, R.id.radioButtonLight));
        editTextFont.setText(sharedPreferences.getString(KEY_FONT, ""));

        buttonSave.setOnClickListener(v -> {
            // Save preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_VOLUME, seekBarVolume.getProgress());
            editor.putBoolean(KEY_SOUND, switchSound.isChecked());
            editor.putInt(KEY_THEME, radioGroupTheme.getCheckedRadioButtonId());
            editor.putString(KEY_FONT, editTextFont.getText().toString());
            editor.apply();

            Toast.makeText(SettingsActivity.this, "Settings saved", Toast.LENGTH_SHORT).show();
        });
    }

    private void checkRadioButton(int radioButtonId) {
        RadioButton radioButton = findViewById(radioButtonId);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
    }

    public static final String KEY_VOLUME = "volume";
    public static final String KEY_SOUND = "sound";
    public static final String KEY_THEME = "theme";
    public static final String KEY_FONT = "font";
}
