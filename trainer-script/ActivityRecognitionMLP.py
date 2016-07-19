import tensorflow as tf
import shutil
import os.path


# Parameters
learning_rate = 0.001
training_epochs = 100
batch_size = 100
display_step = 5

# Network Parameters
n_hidden_1 = 500 # 1st layer number of features
n_hidden_2 = 500 # 2nd layer number of features
n_input = 3 # Number of inputs
n_classes = 6 # Number of classes

g = tf.Graph()
with g.as_default():
    # model inputs
    x = tf.placeholder("float", shape=[None, n_input])
    y = tf.placeholder("float", shape=[None, n_classes])

    # set model weights
    W_h1 = tf.Variable(tf.random_normal([n_input, n_hidden_1]))
    W_h2 = tf.Variable(tf.random_normal([n_hidden_1, n_hidden_2]))
    W_out = tf.Variable(tf.random_normal([n_hidden_2, n_classes]))

    # set model biases
    b1 = tf.Variable(tf.random_normal([n_hidden_1]))
    b2 = tf.Variable(tf.random_normal([n_hidden_2]))
    b_out = tf.Variable(tf.random_normal([n_classes]))

    # Construct Model
    # Hidden layer with RELU activation
    layer_1 = tf.add(tf.matmul(x, W_h1), b1)
    layer_1 = tf.nn.relu(layer_1)
    # Hidden layer with RELU activation
    layer_2 = tf.add(tf.matmul(layer_1, W_h2), b2)
    layer_2 = tf.nn.relu(layer_2)
    # Output layer with linear activation
    pred = tf.matmul(layer_2, W_out) + b_out

    # Define loss and optimizer
    cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(pred, y))
    optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(cost)

    # Initializing the variables
    init = tf.initialize_all_variables()

    sess = tf.Session()

    sess.run(init)

    # Training cycle
    for epoch in range(training_epochs):
        avg_cost = 0.
        _, c = sess.run([optimizer, cost], feed_dict={x: X_train,y: y_train})

        # Compute average loss
        #avg_cost += c / total_batch
        # Display logs per epoch step
        if (epoch+1) % display_step == 0:
            print "Epoch:", '%04d' % (epoch+1), "cost=", "{:.9f}".format(c)

    print "Optimization Finished!"

    # Test model
    correct_prediction = tf.equal(tf.argmax(pred, 1), tf.argmax(y, 1))
    # Calculate accuracy for 3000 examples
    accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
    print "Accuracy:", accuracy.eval({x: X_test, y: y_test}, sess)

# Store Variable
_W_h1 = W_h1.eval(sess)
_W_h2 = W_h2.eval(sess)
_W_out =W_out.eval(sess)

_b1 = b1.eval(sess)
_b2 = b2.eval(sess)
_b_out = b_out.eval(sess)

sess.close()

# create a new graph for exporting
g_2 = tf.Graph()
with g_2.as_default():
    # Reconstruct Graph
    # model inputs
    x_2 = tf.placeholder("float", shape=[None, n_input], name="input")


    # set model weights
    W_2_h1 = tf.constant(_W_h1, name="constant_W_h1")
    W_2_h2 = tf.constant(_W_h2, name="constant_W_h2")
    W_2_out = tf.constant(_W_out, name="constant_W_out")

    # set model biases
    b_2_1 = tf.constant(_b1, name="constant_b1")
    b_2_2 = tf.constant(_b2, name="constant_b2")
    b_2_out = tf.constant(_b_out, name="constant_b_out")

    # Construct Model
    # Hidden layer with RELU activation
    layer_2_1 = tf.add(tf.matmul(x_2, W_2_h1), b_2_1)
    layer_2_1 = tf.nn.relu(layer_2_1)
    # Hidden layer with RELU activation
    layer_2_2 = tf.add(tf.matmul(layer_2_1, W_2_h2), b_2_2)
    layer_2_2 = tf.nn.relu(layer_2_2)

    # Output layer with linear activation
    y_2 = tf.nn.bias_add(tf.matmul(layer_2_2, W_2_out), b_2_out, name="output")

    #y_2.name = "output"

    sess_2 = tf.Session()

    init_2 = tf.initialize_all_variables();
    sess_2.run(init_2)


    graph_def = g_2.as_graph_def()

    tf.train.write_graph(graph_def, 'Models','activityModelMLP.pb', as_text=False)

    # Test trained model
    y__2 = tf.placeholder("float", [None, 6])
    correct_prediction_2 = tf.equal(tf.argmax(y_2, 1), tf.argmax(y__2, 1))
    accuracy_2 = tf.reduce_mean(tf.cast(correct_prediction_2, "float"))
    print(accuracy_2.eval({x_2: X_test, y__2: y_test}, sess_2))