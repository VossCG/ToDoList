package com.voss.todolist.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.voss.todolist.Data.EventTypes;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class UpdateEventFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private UpdateEventFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private UpdateEventFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static UpdateEventFragmentArgs fromBundle(@NonNull Bundle bundle) {
    UpdateEventFragmentArgs __result = new UpdateEventFragmentArgs();
    bundle.setClassLoader(UpdateEventFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("EventTypes")) {
      EventTypes EventTypes;
      if (Parcelable.class.isAssignableFrom(EventTypes.class) || Serializable.class.isAssignableFrom(EventTypes.class)) {
        EventTypes = (EventTypes) bundle.get("EventTypes");
      } else {
        throw new UnsupportedOperationException(EventTypes.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (EventTypes == null) {
        throw new IllegalArgumentException("Argument \"EventTypes\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("EventTypes", EventTypes);
    } else {
      throw new IllegalArgumentException("Required argument \"EventTypes\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static UpdateEventFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    UpdateEventFragmentArgs __result = new UpdateEventFragmentArgs();
    if (savedStateHandle.contains("EventTypes")) {
      EventTypes EventTypes;
      EventTypes = savedStateHandle.get("EventTypes");
      if (EventTypes == null) {
        throw new IllegalArgumentException("Argument \"EventTypes\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("EventTypes", EventTypes);
    } else {
      throw new IllegalArgumentException("Required argument \"EventTypes\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public EventTypes getEventTypes() {
    return (EventTypes) arguments.get("EventTypes");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("EventTypes")) {
      EventTypes EventTypes = (EventTypes) arguments.get("EventTypes");
      if (Parcelable.class.isAssignableFrom(EventTypes.class) || EventTypes == null) {
        __result.putParcelable("EventTypes", Parcelable.class.cast(EventTypes));
      } else if (Serializable.class.isAssignableFrom(EventTypes.class)) {
        __result.putSerializable("EventTypes", Serializable.class.cast(EventTypes));
      } else {
        throw new UnsupportedOperationException(EventTypes.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("EventTypes")) {
      EventTypes EventTypes = (EventTypes) arguments.get("EventTypes");
      if (Parcelable.class.isAssignableFrom(EventTypes.class) || EventTypes == null) {
        __result.set("EventTypes", Parcelable.class.cast(EventTypes));
      } else if (Serializable.class.isAssignableFrom(EventTypes.class)) {
        __result.set("EventTypes", Serializable.class.cast(EventTypes));
      } else {
        throw new UnsupportedOperationException(EventTypes.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
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
    UpdateEventFragmentArgs that = (UpdateEventFragmentArgs) object;
    if (arguments.containsKey("EventTypes") != that.arguments.containsKey("EventTypes")) {
      return false;
    }
    if (getEventTypes() != null ? !getEventTypes().equals(that.getEventTypes()) : that.getEventTypes() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getEventTypes() != null ? getEventTypes().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UpdateEventFragmentArgs{"
        + "EventTypes=" + getEventTypes()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull UpdateEventFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EventTypes EventTypes) {
      if (EventTypes == null) {
        throw new IllegalArgumentException("Argument \"EventTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("EventTypes", EventTypes);
    }

    @NonNull
    public UpdateEventFragmentArgs build() {
      UpdateEventFragmentArgs result = new UpdateEventFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setEventTypes(@NonNull EventTypes EventTypes) {
      if (EventTypes == null) {
        throw new IllegalArgumentException("Argument \"EventTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("EventTypes", EventTypes);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public EventTypes getEventTypes() {
      return (EventTypes) arguments.get("EventTypes");
    }
  }
}
