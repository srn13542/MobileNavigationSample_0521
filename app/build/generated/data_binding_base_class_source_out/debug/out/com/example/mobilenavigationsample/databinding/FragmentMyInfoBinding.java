// Generated by view binder compiler. Do not edit!
package com.example.mobilenavigationsample.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mobilenavigationsample.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMyInfoBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText Age;

  @NonNull
  public final RadioButton BtnMan;

  @NonNull
  public final RadioButton BtnWoman;

  @NonNull
  public final EditText Height;

  @NonNull
  public final TextView Nickname;

  @NonNull
  public final EditText TargetWeight;

  @NonNull
  public final EditText Weight;

  @NonNull
  public final Button buttonSave;

  @NonNull
  public final LinearLayout main;

  @NonNull
  public final RadioGroup radioGroupGender;

  @NonNull
  public final TextView textViewAge;

  @NonNull
  public final TextView textViewGender;

  @NonNull
  public final TextView textViewHeight;

  @NonNull
  public final TextView textViewNickname;

  @NonNull
  public final TextView textViewTargetWeight;

  @NonNull
  public final TextView textViewTitle;

  @NonNull
  public final TextView textViewWeight;

  private FragmentMyInfoBinding(@NonNull RelativeLayout rootView, @NonNull EditText Age,
      @NonNull RadioButton BtnMan, @NonNull RadioButton BtnWoman, @NonNull EditText Height,
      @NonNull TextView Nickname, @NonNull EditText TargetWeight, @NonNull EditText Weight,
      @NonNull Button buttonSave, @NonNull LinearLayout main, @NonNull RadioGroup radioGroupGender,
      @NonNull TextView textViewAge, @NonNull TextView textViewGender,
      @NonNull TextView textViewHeight, @NonNull TextView textViewNickname,
      @NonNull TextView textViewTargetWeight, @NonNull TextView textViewTitle,
      @NonNull TextView textViewWeight) {
    this.rootView = rootView;
    this.Age = Age;
    this.BtnMan = BtnMan;
    this.BtnWoman = BtnWoman;
    this.Height = Height;
    this.Nickname = Nickname;
    this.TargetWeight = TargetWeight;
    this.Weight = Weight;
    this.buttonSave = buttonSave;
    this.main = main;
    this.radioGroupGender = radioGroupGender;
    this.textViewAge = textViewAge;
    this.textViewGender = textViewGender;
    this.textViewHeight = textViewHeight;
    this.textViewNickname = textViewNickname;
    this.textViewTargetWeight = textViewTargetWeight;
    this.textViewTitle = textViewTitle;
    this.textViewWeight = textViewWeight;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMyInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMyInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_my_info, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMyInfoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Age;
      EditText Age = ViewBindings.findChildViewById(rootView, id);
      if (Age == null) {
        break missingId;
      }

      id = R.id.BtnMan;
      RadioButton BtnMan = ViewBindings.findChildViewById(rootView, id);
      if (BtnMan == null) {
        break missingId;
      }

      id = R.id.BtnWoman;
      RadioButton BtnWoman = ViewBindings.findChildViewById(rootView, id);
      if (BtnWoman == null) {
        break missingId;
      }

      id = R.id.Height;
      EditText Height = ViewBindings.findChildViewById(rootView, id);
      if (Height == null) {
        break missingId;
      }

      id = R.id.Nickname;
      TextView Nickname = ViewBindings.findChildViewById(rootView, id);
      if (Nickname == null) {
        break missingId;
      }

      id = R.id.TargetWeight;
      EditText TargetWeight = ViewBindings.findChildViewById(rootView, id);
      if (TargetWeight == null) {
        break missingId;
      }

      id = R.id.Weight;
      EditText Weight = ViewBindings.findChildViewById(rootView, id);
      if (Weight == null) {
        break missingId;
      }

      id = R.id.buttonSave;
      Button buttonSave = ViewBindings.findChildViewById(rootView, id);
      if (buttonSave == null) {
        break missingId;
      }

      id = R.id.main;
      LinearLayout main = ViewBindings.findChildViewById(rootView, id);
      if (main == null) {
        break missingId;
      }

      id = R.id.radioGroupGender;
      RadioGroup radioGroupGender = ViewBindings.findChildViewById(rootView, id);
      if (radioGroupGender == null) {
        break missingId;
      }

      id = R.id.textViewAge;
      TextView textViewAge = ViewBindings.findChildViewById(rootView, id);
      if (textViewAge == null) {
        break missingId;
      }

      id = R.id.textViewGender;
      TextView textViewGender = ViewBindings.findChildViewById(rootView, id);
      if (textViewGender == null) {
        break missingId;
      }

      id = R.id.textViewHeight;
      TextView textViewHeight = ViewBindings.findChildViewById(rootView, id);
      if (textViewHeight == null) {
        break missingId;
      }

      id = R.id.textViewNickname;
      TextView textViewNickname = ViewBindings.findChildViewById(rootView, id);
      if (textViewNickname == null) {
        break missingId;
      }

      id = R.id.textViewTargetWeight;
      TextView textViewTargetWeight = ViewBindings.findChildViewById(rootView, id);
      if (textViewTargetWeight == null) {
        break missingId;
      }

      id = R.id.textViewTitle;
      TextView textViewTitle = ViewBindings.findChildViewById(rootView, id);
      if (textViewTitle == null) {
        break missingId;
      }

      id = R.id.textViewWeight;
      TextView textViewWeight = ViewBindings.findChildViewById(rootView, id);
      if (textViewWeight == null) {
        break missingId;
      }

      return new FragmentMyInfoBinding((RelativeLayout) rootView, Age, BtnMan, BtnWoman, Height,
          Nickname, TargetWeight, Weight, buttonSave, main, radioGroupGender, textViewAge,
          textViewGender, textViewHeight, textViewNickname, textViewTargetWeight, textViewTitle,
          textViewWeight);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
