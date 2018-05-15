/*
  MapUtil exposes a set of helper methods for working with
  ReadableMap (by React Native), Map<String, Object>, and JSONObject.
 */

package com.rtmp_android;

// import com.facebook.react.bridge.Arguments;
// import com.facebook.react.bridge.ReadableMap;
// import com.facebook.react.bridge.ReadableMapKeySetIterator;
// import com.facebook.react.bridge.ReadableType;
// import com.facebook.react.bridge.WritableMap;

// import java.util.Map;
// import java.util.HashMap;
// import java.util.Iterator;

// import org.json.JSONArray;
// import org.json.JSONObject;
// import org.json.JSONException;

// public class MapUtil {

//   public static JSONObject toJSONObject(ReadableMap readableMap) throws JSONException {

//     try {
//       JSONObject jsonObject = new JSONObject();

//       ReadableMapKeySetIterator iterator = readableMap.keySetIterator();

//       while (iterator.hasNextKey()) {
//         String key = iterator.nextKey();
//         ReadableType type = readableMap.getType(key);

//         switch (type) {
//         case Null:
//           jsonObject.put(key, null);
//           break;
//         case Boolean:
//           jsonObject.put(key, readableMap.getBoolean(key));
//           break;
//         case Number:
//           jsonObject.put(key, readableMap.getDouble(key));
//           break;
//         case String:
//           jsonObject.put(key, readableMap.getString(key));
//           break;
//         case Map:
//           jsonObject.put(key, MapUtil.toJSONObject(readableMap.getMap(key)));
//           break;
//         case Array:
//           jsonObject.put(key, ArrayUtil.toJSONArray(readableMap.getArray(key)));
//           break;
//         }
//       }

//       return jsonObject;
//     } catch (JSONException e) {
//       System.out.println("json error");
//       return null;
//     }
//   }

//   public static Map<String, Object> toMap(JSONObject jsonObject) throws JSONException {

//     try {
//       Map<String, Object> map = new HashMap<>();
//       Iterator<String> iterator = jsonObject.keys();

//       while (iterator.hasNext()) {
//         String key = iterator.next();
//         Object value = jsonObject.get(key);

//         if (value instanceof JSONObject) {
//           value = MapUtil.toMap((JSONObject) value);
//         }
//         if (value instanceof JSONArray) {
//           value = ArrayUtil.toArray((JSONArray) value);
//         }

//         map.put(key, value);
//       }
//       return map;

//     } catch (JSONException e) {
//       System.out.println("json error");
//       return null;
//     }

//   }

//   public static Map<String, Object> toMap(ReadableMap readableMap) {

//     Map<String, Object> map = new HashMap<>();
//     ReadableMapKeySetIterator iterator = readableMap.keySetIterator();

//     while (iterator.hasNextKey()) {
//       String key = iterator.nextKey();
//       ReadableType type = readableMap.getType(key);

//       switch (type) {
//       case Null:
//         map.put(key, null);
//         break;
//       case Boolean:
//         map.put(key, readableMap.getBoolean(key));
//         break;
//       case Number:
//         map.put(key, readableMap.getDouble(key));
//         break;
//       case String:
//         map.put(key, readableMap.getString(key));
//         break;
//       case Map:
//         map.put(key, MapUtil.toMap(readableMap.getMap(key)));
//         break;
//       case Array:
//         map.put(key, ArrayUtil.toArray(readableMap.getArray(key)));
//         break;
//       }
//     }

//     return map;

//     // } catch (JSONException e) {
//     // System.out.println("json error");
//     // return null;
//     // }
//   }

//   public static WritableMap toWritableMap(Map<String, Object> map) {
//     WritableMap writableMap = Arguments.createMap();
//     Iterator iterator = map.entrySet().iterator();

//     while (iterator.hasNext()) {
//       Map.Entry pair = (Map.Entry) iterator.next();
//       Object value = pair.getValue();

//       if (value == null) {
//         writableMap.putNull((String) pair.getKey());
//       } else if (value instanceof Boolean) {
//         writableMap.putBoolean((String) pair.getKey(), (Boolean) value);
//       } else if (value instanceof Double) {
//         writableMap.putDouble((String) pair.getKey(), (Double) value);
//       } else if (value instanceof Integer) {
//         writableMap.putInt((String) pair.getKey(), (Integer) value);
//       } else if (value instanceof String) {
//         writableMap.putString((String) pair.getKey(), (String) value);
//       } else if (value instanceof Map) {
//         writableMap.putMap((String) pair.getKey(), MapUtil.toWritableMap((Map<String, Object>) value));
//       } else if (value.getClass() != null && value.getClass().isArray()) {
//         writableMap.putArray((String) pair.getKey(), ArrayUtil.toWritableArray((Object[]) value));
//       }

//       iterator.remove();
//     }

//     return writableMap;
//   }
// }


// import com.facebook.react.bridge.ReadableArray;
// import com.facebook.react.bridge.ReadableMap;
// import com.facebook.react.bridge.ReadableMapKeySetIterator;
// import com.facebook.react.bridge.WritableArray;
// import com.facebook.react.bridge.WritableMap;
// import com.facebook.react.bridge.WritableNativeArray;
// import com.facebook.react.bridge.WritableNativeMap;

// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;

// import java.util.Iterator;

// /**
//  * https://gist.github.com/viperwarp/2beb6bbefcc268dee7ad
//  */
// public class ReactNativeJson {
//   public static WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
//     WritableMap map = new WritableNativeMap();

//     Iterator<String> iterator = jsonObject.keys();
//     while (iterator.hasNext()) {
//       String key = iterator.next();
//       Object value = jsonObject.get(key);
//       if (value instanceof JSONObject) {
//         map.putMap(key, convertJsonToMap((JSONObject) value));
//       } else if (value instanceof JSONArray) {
//         map.putArray(key, convertJsonToArray((JSONArray) value));
//       } else if (value instanceof Boolean) {
//         map.putBoolean(key, (Boolean) value);
//       } else if (value instanceof Integer) {
//         map.putInt(key, (Integer) value);
//       } else if (value instanceof Double) {
//         map.putDouble(key, (Double) value);
//       } else if (value instanceof String) {
//         map.putString(key, (String) value);
//       } else {
//         map.putString(key, value.toString());
//       }
//     }
//     return map;
//   }

//   public static WritableArray convertJsonToArray(JSONArray jsonArray) throws JSONException {
//     WritableArray array = new WritableNativeArray();

//     for (int i = 0; i < jsonArray.length(); i++) {
//       Object value = jsonArray.get(i);
//       if (value instanceof JSONObject) {
//         array.pushMap(convertJsonToMap((JSONObject) value));
//       } else if (value instanceof JSONArray) {
//         array.pushArray(convertJsonToArray((JSONArray) value));
//       } else if (value instanceof Boolean) {
//         array.pushBoolean((Boolean) value);
//       } else if (value instanceof Integer) {
//         array.pushInt((Integer) value);
//       } else if (value instanceof Double) {
//         array.pushDouble((Double) value);
//       } else if (value instanceof String) {
//         array.pushString((String) value);
//       } else {
//         array.pushString(value.toString());
//       }
//     }
//     return array;
//   }

//   public static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
//     JSONObject object = new JSONObject();
//     ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
//     while (iterator.hasNextKey()) {
//       String key = iterator.nextKey();
//       switch (readableMap.getType(key)) {
//       case Null:
//         object.put(key, JSONObject.NULL);
//         break;
//       case Boolean:
//         object.put(key, readableMap.getBoolean(key));
//         break;
//       case Number:
//         object.put(key, readableMap.getDouble(key));
//         break;
//       case String:
//         object.put(key, readableMap.getString(key));
//         break;
//       case Map:
//         object.put(key, convertMapToJson(readableMap.getMap(key)));
//         break;
//       case Array:
//         object.put(key, convertArrayToJson(readableMap.getArray(key)));
//         break;
//       }
//     }
//     return object;
//   }

//   public static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
//     JSONArray array = new JSONArray();
//     for (int i = 0; i < readableArray.size(); i++) {
//       switch (readableArray.getType(i)) {
//       case Null:
//         break;
//       case Boolean:
//         array.put(readableArray.getBoolean(i));
//         break;
//       case Number:
//         array.put(readableArray.getDouble(i));
//         break;
//       case String:
//         array.put(readableArray.getString(i));
//         break;
//       case Map:
//         array.put(convertMapToJson(readableArray.getMap(i)));
//         break;
//       case Array:
//         array.put(convertArrayToJson(readableArray.getArray(i)));
//         break;
//       }
//     }
//     return array;
//   }
// }

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public abstract class ReactNativeJson {
  public static JSONObject reactToJSON(ReadableMap readableMap) throws JSONException {
    JSONObject jsonObject = new JSONObject();
    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
    while (iterator.hasNextKey()) {
      String key = iterator.nextKey();
      ReadableType valueType = readableMap.getType(key);
      switch (valueType) {
      case Null:
        jsonObject.put(key, JSONObject.NULL);
        break;
      case Boolean:
        jsonObject.put(key, readableMap.getBoolean(key));
        break;
      case Number:
        try {
          jsonObject.put(key, readableMap.getInt(key));
        } catch (Exception e) {
          jsonObject.put(key, readableMap.getDouble(key));
        }
        break;
      case String:
        jsonObject.put(key, readableMap.getString(key));
        break;
      case Map:
        jsonObject.put(key, reactToJSON(readableMap.getMap(key)));
        break;
      case Array:
        jsonObject.put(key, reactToJSON(readableMap.getArray(key)));
        break;
      }
    }

    return jsonObject;
  }

  public static JSONArray reactToJSON(ReadableArray readableArray) throws JSONException {
    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < readableArray.size(); i++) {
      ReadableType valueType = readableArray.getType(i);
      switch (valueType) {
      case Null:
        jsonArray.put(JSONObject.NULL);
        break;
      case Boolean:
        jsonArray.put(readableArray.getBoolean(i));
        break;
      case Number:
        try {
          jsonArray.put(readableArray.getInt(i));
        } catch (Exception e) {
          jsonArray.put(readableArray.getDouble(i));
        }
        break;
      case String:
        jsonArray.put(readableArray.getString(i));
        break;
      case Map:
        jsonArray.put(reactToJSON(readableArray.getMap(i)));
        break;
      case Array:
        jsonArray.put(reactToJSON(readableArray.getArray(i)));
        break;
      }
    }
    return jsonArray;
  }

  public static WritableMap jsonToReact(JSONObject jsonObject) throws JSONException {
    WritableMap writableMap = Arguments.createMap();
    Iterator iterator = jsonObject.keys();
    while (iterator.hasNext()) {
      String key = (String) iterator.next();
      Object value = jsonObject.get(key);
      if (value instanceof Float || value instanceof Double) {
        writableMap.putDouble(key, jsonObject.getDouble(key));
      } else if (value instanceof Number) {
        writableMap.putInt(key, jsonObject.getInt(key));
      } else if (value instanceof String) {
        writableMap.putString(key, jsonObject.getString(key));
      } else if (value instanceof JSONObject) {
        writableMap.putMap(key, jsonToReact(jsonObject.getJSONObject(key)));
      } else if (value instanceof JSONArray) {
        writableMap.putArray(key, jsonToReact(jsonObject.getJSONArray(key)));
      } else if (value == JSONObject.NULL) {
        writableMap.putNull(key);
      }
    }

    return writableMap;
  }

  public static WritableArray jsonToReact(JSONArray jsonArray) throws JSONException {
    WritableArray writableArray = Arguments.createArray();
    for (int i = 0; i < jsonArray.length(); i++) {
      Object value = jsonArray.get(i);
      if (value instanceof Float || value instanceof Double) {
        writableArray.pushDouble(jsonArray.getDouble(i));
      } else if (value instanceof Number) {
        writableArray.pushInt(jsonArray.getInt(i));
      } else if (value instanceof String) {
        writableArray.pushString(jsonArray.getString(i));
      } else if (value instanceof JSONObject) {
        writableArray.pushMap(jsonToReact(jsonArray.getJSONObject(i)));
      } else if (value instanceof JSONArray) {
        writableArray.pushArray(jsonToReact(jsonArray.getJSONArray(i)));
      } else if (value == JSONObject.NULL) {
        writableArray.pushNull();
      }
    }
    return writableArray;
  }

  public static Map<String, Object> reactToMap(ReadableMap readableMap) {
    Map<String, Object> map = new HashMap<>();
    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();

    while (iterator.hasNextKey()) {
      String key = iterator.nextKey();
      ReadableType type = readableMap.getType(key);

      switch (type) {
      case Null:
        map.put(key, null);
        break;
      case Boolean:
        map.put(key, readableMap.getBoolean(key));
        break;
      case Number:
        map.put(key, readableMap.getDouble(key));
        break;
      case String:
        map.put(key, readableMap.getString(key));
        break;
      case Map:
        map.put(key, reactToMap(readableMap.getMap(key)));
        break;
      case Array:
        map.put(key, reactToArray(readableMap.getArray(key)));
        break;
      }
    }

    return map;
  }

  public static Object[] reactToArray(ReadableArray readableArray) {
    Object[] array = new Object[readableArray.size()];

    for (int i = 0; i < readableArray.size(); i++) {
      ReadableType type = readableArray.getType(i);

      switch (type) {
      case Null:
        array[i] = null;
        break;
      case Boolean:
        array[i] = readableArray.getBoolean(i);
        break;
      case Number:
        array[i] = readableArray.getDouble(i);
        break;
      case String:
        array[i] = readableArray.getString(i);
        break;
      case Map:
        array[i] = reactToMap(readableArray.getMap(i));
        break;
      case Array:
        array[i] = reactToArray(readableArray.getArray(i));
        break;
      }
    }

    return array;
  }
}