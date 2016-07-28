import mongoose from 'mongoose';

/**
 * Item Schema.
 *
 * Database schema for on activity-item.
 * An item has to belong to an activity and.
 */
const ItemSchema = new mongoose.Schema({

  name: {
    type: String,
    required: true,
  },
  translation: {
    type: String,
    required: false,
    default: '',
  },
  image: {
    type: String,
    required: false,
  },
  enabled: {
    type: Boolean,
    required: false,
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
export default mongoose.model('Item', ItemSchema);
