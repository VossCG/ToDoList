package com.voss.todolist.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class MonthFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private MonthFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private MonthFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static MonthFragmentArgs fromBundle(@NonNull Bundle bundle) {
    MonthFragmentArgs __result = new MonthFragmentArgs();
    bundle.setClassLoader(MonthFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("year")) {
      int year;
      year = bundle.getInt("year");
      __result.arguments.put("year", year);
    } else {
      throw new IllegalArgumentException("Required argument \"year\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static MonthFragmentArgs fromSavedStateHandle(@NonNull SavedStateHandle savedStateHandle) {
    MonthFragmentArgs __result = new MonthFragmentArgs();
    if (savedStateHandle.contains("year")) {
      int year;
      year = savedStateHandle.get("year");
      __result.arguments.put("year", year);
    } else {
      throw new IllegalArgumentException("Required argument \"year\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getYear() {
    return (int) arguments.get("year");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("year")) {
      int year = (int) arguments.get("year");
      __result.putInt("year", year);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("year")) {
      int year = (int) arguments.get("year");
      __result.set("year", year);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    MonthFragmentArgs that = (MonthFragmentArgs) object;
    if (arguments.containsKey("year") != that.arguments.containsKey("year")) {
      return false;
    }
    if (getYear() != that.getYear()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getYear();
    return result;
  }

  @Override
  public String toString() {
    return "MonthFragmentArgs{"
        + "year=" + getYear()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull MonthFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int year) {
      this.arguments.put("year", year);
    }

    @NonNull
    public MonthFragmentArgs build() {
      MonthFragmentArgs result = new MonthFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setYear(int year) {
      this.arguments.put("year", year);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getYear() {
      return (int) arguments.get("year");
    }
  }
}
