import tensorflow as tf
import shutil
import os.path


g = tf.Graph()

with g.as_default():
    # Create the model

    # tf Graph Input
    x = tf.placeholder(tf.float32, [None, 3]) # 3 inputs
    y = tf.placeholder(tf.float32, [None, 6]) # 6 classes

    # Set model weights
    W = tf.Variable(tf.zeros([3, 6]))
    b = tf.Variable(tf.zeros([6]))

    # Construct model
    pred = tf.nn.softmax(tf.matmul(x, W) + b) # Softmax

    # Minimize error using cross entropy
    cost = tf.reduce_mean(-tf.reduce_sum(y*tf.log(pred), reduction_indices=1))
    # Gradient Descent
    optimizer = tf.train.GradientDescentOptimizer(learning_rate).minimize(cost)

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

# Store variable
_W = W.eval(sess)
_b = b.eval(sess)


sess.close()

#Create new graph for exporting
g_2 = tf.Graph()
with g_2.as_default():
    # Reconstruct graph
    x_2 = tf.placeholder("float", [None, 3], name="input")
    W_2 = tf.constant(_W, name="constant_W")
    b_2 = tf.constant(_b, name="constant_b")
    y_2 = tf.nn.softmax(tf.matmul(x_2, W_2) + b_2, name="output")

    sess_2 = tf.Session()

    init_2 = tf.initialize_all_variables();
    sess_2.run(init_2)


    graph_def = g_2.as_graph_def()

    tf.train.write_graph(graph_def, 'Models','activityModel.pb', as_text=False)

    # Test trained model
    y__2 = tf.placeholder("float", [None, 6])
    correct_prediction_2 = tf.equal(tf.argmax(y_2, 1), tf.argmax(y__2, 1))
    accuracy_2 = tf.reduce_mean(tf.cast(correct_prediction_2, "float"))
    print(accuracy_2.eval({x_2: X_test, y__2: y_test}, sess_2))

