package com.voss.todolist.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.voss.todolist.Adapter.ArgsToContent;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ContentMonthlyFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ContentMonthlyFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ContentMonthlyFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ContentMonthlyFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ContentMonthlyFragmentArgs __result = new ContentMonthlyFragmentArgs();
    bundle.setClassLoader(ContentMonthlyFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("ContentArgs")) {
      ArgsToContent ContentArgs;
      if (Parcelable.class.isAssignableFrom(ArgsToContent.class) || Serializable.class.isAssignableFrom(ArgsToContent.class)) {
        ContentArgs = (ArgsToContent) bundle.get("ContentArgs");
      } else {
        throw new UnsupportedOperationException(ArgsToContent.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("ContentArgs", ContentArgs);
    } else {
      throw new IllegalArgumentException("Required argument \"ContentArgs\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ContentMonthlyFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    ContentMonthlyFragmentArgs __result = new ContentMonthlyFragmentArgs();
    if (savedStateHandle.contains("ContentArgs")) {
      ArgsToContent ContentArgs;
      ContentArgs = savedStateHandle.get("ContentArgs");
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("ContentArgs", ContentArgs);
    } else {
      throw new IllegalArgumentException("Required argument \"ContentArgs\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public ArgsToContent getContentArgs() {
    return (ArgsToContent) arguments.get("ContentArgs");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("ContentArgs")) {
      ArgsToContent ContentArgs = (ArgsToContent) arguments.get("ContentArgs");
      if (Parcelable.class.isAssignableFrom(ArgsToContent.class) || ContentArgs == null) {
        __result.putParcelable("ContentArgs", Parcelable.class.cast(ContentArgs));
      } else if (Serializable.class.isAssignableFrom(ArgsToContent.class)) {
        __result.putSerializable("ContentArgs", Serializable.class.cast(ContentArgs));
      } else {
        throw new UnsupportedOperationException(ArgsToContent.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("ContentArgs")) {
      ArgsToContent ContentArgs = (ArgsToContent) arguments.get("ContentArgs");
      if (Parcelable.class.isAssignableFrom(ArgsToContent.class) || ContentArgs == null) {
        __result.set("ContentArgs", Parcelable.class.cast(ContentArgs));
      } else if (Serializable.class.isAssignableFrom(ArgsToContent.class)) {
        __result.set("ContentArgs", Serializable.class.cast(ContentArgs));
      } else {
        throw new UnsupportedOperationException(ArgsToContent.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
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
    ContentMonthlyFragmentArgs that = (ContentMonthlyFragmentArgs) object;
    if (arguments.containsKey("ContentArgs") != that.arguments.containsKey("ContentArgs")) {
      return false;
    }
    if (getContentArgs() != null ? !getContentArgs().equals(that.getContentArgs()) : that.getContentArgs() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getContentArgs() != null ? getContentArgs().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContentMonthlyFragmentArgs{"
        + "ContentArgs=" + getContentArgs()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ContentMonthlyFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ArgsToContent ContentArgs) {
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ContentArgs", ContentArgs);
    }

    @NonNull
    public ContentMonthlyFragmentArgs build() {
      ContentMonthlyFragmentArgs result = new ContentMonthlyFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setContentArgs(@NonNull ArgsToContent ContentArgs) {
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ContentArgs", ContentArgs);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public ArgsToContent getContentArgs() {
      return (ArgsToContent) arguments.get("ContentArgs");
    }
  }
}
