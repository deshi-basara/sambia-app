import mongoose from 'mongoose';

/**
 * Activity Schema.
 *
 * Database schema for on activity. A activity has to be in a group and
 * can have a items describing the activity.
 */
const ActivitySchema = new mongoose.Schema({

  name: {
    type: String,
    required: true,
  },
  translation: {
    type: String,
    required: true,
    default: {},
  },
  image: {
    type: String,
    required: true,
  },
  items: {
    type: [Number],
    required: true,
  },
  enabled: {
    type: Boolean,
    required: true,
    default: false,
  },
  createdAt: {
    type: Date,
    default: Date.now,
  },

});

/**
 * @typedef Activity
 */
export default mongoose.model('Activity', ActivitySchema);
